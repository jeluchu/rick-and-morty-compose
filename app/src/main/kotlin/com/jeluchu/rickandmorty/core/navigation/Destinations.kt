package com.jeluchu.rickandmorty.core.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.jeluchu.rickandmorty.core.extensions.lifecycleIsResumed
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.details.navigation.CharacterRoutes
import com.jeluchu.rickandmorty.features.favorites.navigation.FavoriteRoutes
import kotlinx.serialization.json.Json

class Destinations(private val navController: NavHostController) {
    val goToFavorites: () -> Unit = { navController.navigate(FavoriteRoutes.Liked) }
    val goToCharacterDetails: (Character) -> Unit = { character ->
        goToWithExtras(CharacterRoutes.Details(Json.encodeToString(character)))
    }

    val goBack: (from: NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) navController.popBackStack()
    }

    private fun <T : Any> goToWithExtras(route: T) {
        navController.navigate(route)
    }
}