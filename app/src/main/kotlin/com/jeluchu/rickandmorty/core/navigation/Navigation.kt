package com.jeluchu.rickandmorty.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.jeluchu.rickandmorty.features.details.navigation.characterDetailsNavigation
import com.jeluchu.rickandmorty.features.favorites.navigation.favoriteNavigation
import com.jeluchu.rickandmorty.features.home.navigation.HomeRoutes
import com.jeluchu.rickandmorty.features.home.navigation.homeNavigation

@Composable
fun Navigation() = ProvideNavHostController {
    ProvideNavigate {
        val navigate = LocalNavigation.current

        NavHost(
            navController = LocalNavHost.current,
            startDestination = HomeRoutes.Dashboard
        ) {
            homeNavigation(navigate)
            favoriteNavigation(navigate)
            characterDetailsNavigation(navigate)
        }
    }
}
