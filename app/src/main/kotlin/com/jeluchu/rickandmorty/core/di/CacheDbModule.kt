package com.jeluchu.rickandmorty.core.di

import android.content.Context
import androidx.room.Room
import com.jeluchu.rickandmorty.core.database.CacheDatabase
import com.jeluchu.rickandmorty.features.characters.repository.CharactersResultDao
import com.jeluchu.rickandmorty.features.locations.repository.local.LocationsDao
import org.koin.dsl.module

val cacheModule = module {
    single<CacheDatabase> {
        Room.databaseBuilder(
            get<Context>(),
            CacheDatabase::class.java,
            "RickAndMortyCache-db"
        )
            .fallbackToDestructiveMigration(true)
            .allowMainThreadQueries()
            .build()
    }

    single<LocationsDao> { get<CacheDatabase>().locationsDao() }
    single<CharactersResultDao> { get<CacheDatabase>().charactersResultDao() }
}