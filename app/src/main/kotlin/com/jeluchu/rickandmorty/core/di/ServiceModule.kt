package com.jeluchu.rickandmorty.core.di

import com.jeluchu.rickandmorty.features.characters.repository.CharactersService
import com.jeluchu.rickandmorty.features.locations.repository.LocationsService
import org.koin.dsl.module

val serviceModule = module {
    single { LocationsService(get()) }
    single { CharactersService(get()) }
}