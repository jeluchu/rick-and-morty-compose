package com.jeluchu.rickandmorty.core.di

import com.jeluchu.rickandmorty.features.characters.repository.CharactersRepository
import com.jeluchu.rickandmorty.features.characters.repository.FavoritesRepository
import com.jeluchu.rickandmorty.features.home.repository.CustomizationRepository
import com.jeluchu.rickandmorty.features.locations.repository.LocationsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<FavoritesRepository> { FavoritesRepository.FavoritesRepositoryImpl(get()) }
    factory<CustomizationRepository> { CustomizationRepository.CustomizationRepositoryImpl() }
    factory<LocationsRepository> { LocationsRepository.LocationsRepositoryImpl(get(), get(), get()) }
    factory<CharactersRepository> { CharactersRepository.CharactersRepositoryImpl(get(), get(), get()) }
}