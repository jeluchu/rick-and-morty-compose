package com.jeluchu.rickandmorty.core.utils.prefs

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import com.jeluchu.rickandmorty.core.utils.prefs.DataStoreHelpers.Companion.booleanPrefsKey

object PreferencesService {
    private val prefs by lazy { DataStoreHelpers() }

    val isDarkMode
        get() = getPreference(KEY_IS_DARK_MODE, false)

    fun savePreference(
        tag: String,
        value: Long
    ) = prefs.savePreference(longPreferencesKey(tag), value)

    fun savePreference(
        tag: String,
        value: Boolean
    ) = prefs.savePreference(booleanPrefsKey(tag), value)

    fun <T> savePreference(
        tag: Preferences.Key<T>,
        value: T
    ) = prefs.savePreference(tag, value)

    fun savePreference(
        tag: Preferences.Key<Int>,
        value: Int
    ) = prefs.savePreference(tag, value)

    fun savePreference(
        tag: Preferences.Key<Long>,
        value: Long
    ) = prefs.savePreference(tag, value)

    fun savePreference(
        tag: Preferences.Key<String>,
        value: String
    ) = prefs.savePreference(tag, value)

    fun savePreference(
        tag: Preferences.Key<Boolean>,
        value: Boolean
    ) = prefs.savePreference(tag, value)

    private fun <T> getPreference(
        tag: Preferences.Key<T>
    ) = prefs.getPreference(tag)

    private fun getPreference(
        tag: Preferences.Key<Int>,
        default: Int
    ) = prefs.getPreference(tag, default)

    fun getPreference(
        tag: String,
        default: Long
    ) = prefs.getPreference(longPreferencesKey(tag), default)

    fun getPreference(
        tag: String,
        default: Boolean
    ) = prefs.getPreference(booleanPrefsKey(tag), default)

    fun getPreference(
        tag: Preferences.Key<Long>,
        default: Long
    ) = prefs.getPreference(tag, default)

    private fun getPreference(
        tag: Preferences.Key<String>,
        default: String
    ) = prefs.getPreference(tag, default)

    private fun getPreference(
        tag: Preferences.Key<Boolean>,
        default: Boolean
    ) = prefs.getPreference(tag, default)

    val KEY_IS_DARK_MODE = booleanPrefsKey("KEY_IS_DARK_MODE")
}