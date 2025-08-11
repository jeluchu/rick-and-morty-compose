package com.jeluchu.rickandmorty.features.characters.usecase

import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.core.network.Resource
import com.jeluchu.rickandmorty.features.characters.models.CharacterResult
import com.jeluchu.rickandmorty.features.characters.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(
    private val channelRepository: CharactersRepository
): UseCase<Resource<Failure, CharacterResult>, GetCharactersUseCase.Params>() {
    override fun run(params: Params?): Flow<Resource<Failure, CharacterResult>> = channelRepository.getPagedCharacters(params?.page ?: 1)
    data class Params(val page: Int)
}