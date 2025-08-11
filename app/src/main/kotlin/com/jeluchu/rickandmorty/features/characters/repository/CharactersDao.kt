package com.jeluchu.rickandmorty.features.characters.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {
    @Query("SELECT * from CharacterEntity")
    fun getCharacter(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity)

    @Query("DELETE FROM CharacterEntity")
    suspend fun deleteAllCharacters()

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}