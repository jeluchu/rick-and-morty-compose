package com.jeluchu.rickandmorty.features.characters.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationEntity(
    @SerialName("name")
    val name: String? = null,

    @SerialName("url")
    val url: String? = null
) {
    fun toLocation() = Location(
        name = name ?: "",
        url = url ?: ""
    )

    companion object {
        fun empty() = LocationEntity(
            name = "",
            url = ""
        )
    }
}