package com.jeluchu.rickandmorty.core.extensions

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.core.net.toUri

/**
 *
 * [Context] Extension to obtain the necessary information
 * from [ConnectivityManager]
 *
 * @see android.net.ConnectivityManager
 *
 */
inline val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.openLink(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}