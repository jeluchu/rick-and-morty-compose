package com.jeluchu.rickandmorty.features.characters.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersResultDao {
    @Query("SELECT * from CharacterResultEntity WHERE currentPage = :page")
    fun getCharacters(page: Int): Flow<CharacterResultEntity>

    @Query("SELECT * from CharacterResultEntity WHERE currentPage LIKE :page")
    fun getCharactersForServerQuery(page: Int): CharacterResultEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharactersResult(characters: CharacterResultEntity)

    @Query("DELETE FROM CharacterResultEntity WHERE currentPage = :page")
    suspend fun deletePageOfCharacters(page: Int)
}