package com.jeluchu.rickandmorty.core.di

import com.jeluchu.rickandmorty.features.characters.viewmodel.CharactersViewModel
import com.jeluchu.rickandmorty.features.favorites.viewmodel.FavoritesViewModel
import com.jeluchu.rickandmorty.features.home.viewmodel.CustomizationViewModel
import com.jeluchu.rickandmorty.features.locations.viewmodel.LocationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LocationViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }
    viewModel { CustomizationViewModel(get()) }
    viewModel { CharactersViewModel(get(), get(), get(), get()) }
}