package com.jeluchu.rickandmorty.core.ui.theme

import androidx.compose.ui.graphics.Color
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.isDarkMode

val lightGreen =  Color(0xFFECFADC)
val darkGreen =  Color(0xFF718355)
val turquoise =  Color(0xFF7fb5b5)

/** DARK COLORS -------------------------------------------------------------------------------- **/

val darkBackground = Color(0xFF121212)
val darknessButtonBackground = Color(0xFF161616)
val darknessBackground = Color(0xFF202020)
val darkLightBackground = Color(0xFF282828)
val darkLightCreamBackground = Color(0xFF383838)
val darknessLightBackground = Color(0xFF444444)
val darkLight3Background = Color(0xFF757575)
val darkLight2Background = Color(0xFFB3B3B3)
val phoneIcons = Color(0xFF9B9492)

val lightGreenOrDark: Color
    get() = if (isDarkMode) darkBackground else lightGreen