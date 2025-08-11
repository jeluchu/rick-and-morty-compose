package com.jeluchu.rickandmorty.core.di

import android.content.Context
import androidx.room.Room
import com.jeluchu.rickandmorty.core.database.FavoriteDatabase
import com.jeluchu.rickandmorty.features.characters.repository.CharactersDao
import org.koin.dsl.module

val favouriteModule = module {

    single<FavoriteDatabase> {
        Room.databaseBuilder(
            get<Context>(),
            FavoriteDatabase::class.java,
            "RickAndMortyFavorites-db"
        )
            .fallbackToDestructiveMigration(false)
            .allowMainThreadQueries()
            .build()
    }

    single<CharactersDao> { get<FavoriteDatabase>().charactersDao() }
}