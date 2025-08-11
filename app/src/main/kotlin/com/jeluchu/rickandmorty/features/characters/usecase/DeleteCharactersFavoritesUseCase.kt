package com.jeluchu.rickandmorty.features.characters.usecase

import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.characters.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteCharactersFavoritesUseCase(
    private val channelRepository: FavoritesRepository
): UseCase<Unit, DeleteCharactersFavoritesUseCase.Params>() {
    override fun run(params: Params?): Flow<Unit> = flow {
        channelRepository.deleteCharacter(params?.character ?: Character.empty())
    }

    data class Params(val character: Character)
}