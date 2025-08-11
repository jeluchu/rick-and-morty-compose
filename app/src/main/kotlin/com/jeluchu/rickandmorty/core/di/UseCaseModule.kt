package com.jeluchu.rickandmorty.core.di

import com.jeluchu.rickandmorty.features.characters.usecase.DeleteCharactersFavoritesUseCase
import com.jeluchu.rickandmorty.features.characters.usecase.GetCharactersFavoritesUseCase
import com.jeluchu.rickandmorty.features.characters.usecase.InsertCharactersFavoritesUseCase
import com.jeluchu.rickandmorty.features.characters.usecase.GetCharactersUseCase
import com.jeluchu.rickandmorty.features.favorites.usecase.GetFavoritesUseCase
import com.jeluchu.rickandmorty.features.home.viewmodel.SetThemeUseCase
import com.jeluchu.rickandmorty.features.locations.usecase.GetLocationUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<SetThemeUseCase> { SetThemeUseCase(get()) }
    factory<GetLocationUseCase> { GetLocationUseCase(get()) }
    factory<GetFavoritesUseCase> { GetFavoritesUseCase(get()) }
    factory<GetCharactersUseCase> { GetCharactersUseCase(get()) }
    factory<GetCharactersFavoritesUseCase> { GetCharactersFavoritesUseCase(get()) }
    factory<InsertCharactersFavoritesUseCase> { InsertCharactersFavoritesUseCase(get()) }
    factory<DeleteCharactersFavoritesUseCase> { DeleteCharactersFavoritesUseCase(get()) }
}