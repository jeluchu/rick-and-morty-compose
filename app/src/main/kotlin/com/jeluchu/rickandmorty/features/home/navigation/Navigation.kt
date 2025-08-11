package com.jeluchu.rickandmorty.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeluchu.rickandmorty.core.extensions.openLink
import com.jeluchu.rickandmorty.core.navigation.Destinations
import com.jeluchu.rickandmorty.core.utils.Links
import com.jeluchu.rickandmorty.features.home.view.DashboardView

internal fun NavGraphBuilder.homeNavigation(
    nav: Destinations
) = composable<HomeRoutes.Dashboard> {
    DashboardView(
        onFavoriteClick = nav.goToFavorites,
        onDarkModeClick = { vm -> vm.setTheme() },
        onSaveCheck = { vm, id -> vm.isSaved(id) },
        onGitHubClick = { ctx -> ctx.openLink(Links.GITHUB) },
        onCharacterDetailsClick = { character -> nav.goToCharacterDetails(character) }
    )
}