package com.jeluchu.rickandmorty.features.locations.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity
import com.jeluchu.rickandmorty.features.locations.models.LocationResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationsDao {
    @Query("SELECT * from LocationResultEntity WHERE currentPage = :page")
    fun getLocations(page: Int): Flow<LocationResultEntity>

    @Query("SELECT * from LocationResultEntity WHERE currentPage LIKE :page")
    fun getLocationsForServerQuery(page: Int): LocationResultEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationsResult(characters: LocationResultEntity)

    @Query("DELETE FROM LocationResultEntity WHERE currentPage = :page")
    suspend fun deletePageOfLocations(page: Int)
}