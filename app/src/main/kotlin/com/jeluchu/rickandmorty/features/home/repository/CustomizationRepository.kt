package com.jeluchu.rickandmorty.features.home.repository

import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.KEY_IS_DARK_MODE
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.isDarkMode
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.savePreference

interface CustomizationRepository {
    fun setTheme()

    class CustomizationRepositoryImpl() : CustomizationRepository {
        override fun setTheme() = savePreference(KEY_IS_DARK_MODE, !isDarkMode)
    }
}