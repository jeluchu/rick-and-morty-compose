package com.jeluchu.rickandmorty.features.home.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class HomeRoutes {
    @Serializable
    data object Dashboard : HomeRoutes()

    @Serializable
    data object Navigation : HomeRoutes()

    @Serializable
    data object Favorites : HomeRoutes()

    @Serializable
    data class Details(val id: String) : HomeRoutes()
}