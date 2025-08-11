package com.jeluchu.rickandmorty.features.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeluchu.rickandmorty.core.extensions.flowCollector
import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.features.characters.state.CharactersState
import com.jeluchu.rickandmorty.features.favorites.usecase.GetFavoritesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoritesViewModel(
    private val getCharacters: GetFavoritesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CharactersState())
    val state: StateFlow<CharactersState> by lazy { _state.asStateFlow() }

    init {
        getLocalData()
    }

    private fun getLocalData() =
        getCharacters(UseCase.None()).flowCollector(viewModelScope) { task ->
            _state.update { it.copy(favorites = task) }
        }
}