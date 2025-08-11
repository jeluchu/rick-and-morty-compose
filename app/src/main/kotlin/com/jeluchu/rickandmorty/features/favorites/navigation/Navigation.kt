package com.jeluchu.rickandmorty.features.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeluchu.rickandmorty.core.navigation.Destinations
import com.jeluchu.rickandmorty.features.favorites.view.FavoritesView

internal fun NavGraphBuilder.favoriteNavigation(
    nav: Destinations
) = composable<FavoriteRoutes.Liked> { backStackEntry ->
    FavoritesView(onBackClick = { nav.goBack(backStackEntry) })
}