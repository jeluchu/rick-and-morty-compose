package com.jeluchu.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import android.graphics.Color
import com.jeluchu.rickandmorty.core.navigation.Navigation
import com.jeluchu.rickandmorty.core.ui.theme.RickAndMortyTheme
import com.jeluchu.rickandmorty.core.utils.prefs.DataStoreHelpers
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        setContent {
            val theme by DataStoreHelpers.rememberPreference(
                PreferencesService.KEY_IS_DARK_MODE,
                PreferencesService.isDarkMode
            )

            RickAndMortyTheme(darkTheme = theme) {
                Navigation()
            }
        }
    }
}