package com.jeluchu.rickandmorty.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import com.jeluchu.rickandmorty.features.characters.repository.CharactersDao
import com.jeluchu.rickandmorty.features.characters.repository.CharactersResultDao
import com.jeluchu.rickandmorty.features.locations.repository.local.LocationsDao

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        CharacterEntity::class,
    ]
)
@TypeConverters(value = [ModelsConverter::class])
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
