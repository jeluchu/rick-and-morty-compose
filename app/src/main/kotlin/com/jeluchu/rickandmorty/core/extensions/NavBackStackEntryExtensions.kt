package com.jeluchu.rickandmorty.core.extensions

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry

fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED