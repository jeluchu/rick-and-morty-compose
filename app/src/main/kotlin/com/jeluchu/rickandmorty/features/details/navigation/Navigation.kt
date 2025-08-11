package com.jeluchu.rickandmorty.features.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeluchu.rickandmorty.core.navigation.Destinations
import com.jeluchu.rickandmorty.features.details.view.CharacterDetailsView
import kotlinx.serialization.json.Json
import com.jeluchu.rickandmorty.features.characters.models.Character

internal fun NavGraphBuilder.characterDetailsNavigation(
    nav: Destinations
) = composable<CharacterRoutes.Details> { backStackEntry ->
    val characterJson = backStackEntry.arguments?.getString("character")
    val character = characterJson?.let { Json.decodeFromString<Character>(it) }

    CharacterDetailsView(
        character = character,
        onBackClick = { nav.goBack(backStackEntry) }
    )
}