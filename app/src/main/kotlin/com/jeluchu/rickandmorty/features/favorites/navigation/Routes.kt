package com.jeluchu.rickandmorty.features.favorites.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class FavoriteRoutes {
    @Serializable
    data object Liked : FavoriteRoutes()

    @Serializable
    data object Navigation : FavoriteRoutes()

    @Serializable
    data object Favorites : FavoriteRoutes()

    @Serializable
    data class Details(val id: String) : FavoriteRoutes()
}