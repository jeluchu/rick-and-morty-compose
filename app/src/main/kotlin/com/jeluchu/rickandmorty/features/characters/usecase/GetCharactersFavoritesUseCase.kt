package com.jeluchu.rickandmorty.features.characters.usecase

import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import com.jeluchu.rickandmorty.features.characters.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetCharactersFavoritesUseCase(
    private val channelRepository: FavoritesRepository
): UseCase<List<CharacterEntity>, UseCase.None>() {
    override fun run(params: None?): Flow<List<CharacterEntity>> = channelRepository.getCharacters()
}