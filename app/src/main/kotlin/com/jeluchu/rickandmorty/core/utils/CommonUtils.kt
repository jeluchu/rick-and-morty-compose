package com.jeluchu.rickandmorty.core.utils

import java.util.Date

fun isFetchSixHours(lastFetchTime: Long): Boolean =
    Date().time - lastFetchTime >= 6 * 60 * 60 * 1000