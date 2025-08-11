package com.jeluchu.rickandmorty.features.home.viewmodel

import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.features.home.repository.CustomizationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SetThemeUseCase(
    private val settingsRepository: CustomizationRepository
): UseCase<Unit, UseCase.None>() {
    
    override fun run(params: None?): Flow<Unit> = flow { settingsRepository.setTheme() }
}