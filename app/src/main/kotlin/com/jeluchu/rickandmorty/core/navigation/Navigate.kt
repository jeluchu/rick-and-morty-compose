package com.jeluchu.rickandmorty.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

private val LocalNavHostController = staticCompositionLocalOf<NavHostController> {
    error("No NavHostController provided")
}

private val LocalNavigate = staticCompositionLocalOf<Destinations> {
    error("No Navigate provided")
}

object LocalNavHost {
    val current: NavHostController
        @Composable
        get() = LocalNavHostController.current
}

object LocalNavigation {
    val current: Destinations
        @Composable
        get() = LocalNavigate.current
}

@Composable
fun ProvideNavHostController(content: @Composable () -> Unit) {
    val navController = rememberNavController()
    CompositionLocalProvider(
        LocalNavHostController provides navController,
        content = content
    )
}

@Composable
fun ProvideNavigate(content: @Composable () -> Unit) {
    val navController = LocalNavHostController.current
    val navigate = remember(navController) { Destinations(navController) }
    CompositionLocalProvider(
        LocalNavigate provides navigate,
        content = content
    )
}