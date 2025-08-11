package com.jeluchu.rickandmorty.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeluchu.rickandmorty.core.interactor.UseCase
import kotlinx.coroutines.flow.launchIn

class CustomizationViewModel(
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {
    fun setTheme() = setThemeUseCase(UseCase.None()).launchIn(viewModelScope)
}