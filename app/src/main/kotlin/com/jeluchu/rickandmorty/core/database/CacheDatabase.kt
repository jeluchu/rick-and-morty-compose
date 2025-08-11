package com.jeluchu.rickandmorty.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity
import com.jeluchu.rickandmorty.features.characters.repository.CharactersResultDao
import com.jeluchu.rickandmorty.features.locations.models.LocationResultEntity
import com.jeluchu.rickandmorty.features.locations.repository.local.LocationsDao

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        LocationResultEntity::class,
        CharacterResultEntity::class,
    ]
)
@TypeConverters(value = [ModelsConverter::class])
abstract class CacheDatabase : RoomDatabase() {
    abstract fun locationsDao(): LocationsDao
    abstract fun charactersResultDao(): CharactersResultDao
}
