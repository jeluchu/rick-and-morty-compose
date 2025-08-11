package com.jeluchu.rickandmorty.features.characters.repository

import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import kotlinx.coroutines.flow.Flow
import com.jeluchu.rickandmorty.features.characters.models.Character

interface FavoritesRepository {
    fun insertCharacter(character: Character)
    fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun deleteCharacter(character: Character)

    class FavoritesRepositoryImpl(
        private val dao: CharactersDao
    ) : FavoritesRepository {
        override fun getCharacters(): Flow<List<CharacterEntity>> = dao.getCharacter()
        override fun insertCharacter(character: Character) = dao.insertCharacter(character.toCharacterEntity())
        override suspend fun deleteCharacter(character: Character) = dao.deleteCharacter(character.toCharacterEntity())
    }
}