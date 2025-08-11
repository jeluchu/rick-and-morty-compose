package com.jeluchu.rickandmorty.features.characters.models

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val url: String
) {
    fun toLocationEntity() = LocationEntity(
        url = url,
        name = name
    )

    companion object {
        fun empty() = Location(
            name = "",
            url = ""
        )
    }
}