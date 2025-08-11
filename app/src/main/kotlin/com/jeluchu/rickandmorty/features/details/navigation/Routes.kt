package com.jeluchu.rickandmorty.features.details.navigation

import com.jeluchu.rickandmorty.features.characters.models.Character
import kotlinx.serialization.Serializable

@Serializable
sealed class CharacterRoutes {
    @Serializable
    data class Details(val character: String) : CharacterRoutes()
}