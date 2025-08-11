package com.jeluchu.rickandmorty.features.characters.models

import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    val name: String,
    val url: String
) {
    fun toOriginEntity() = OriginEntity(
        url = url,
        name = name
    )

    companion object {
        fun empty() = Origin(
            name = "",
            url = ""
        )
    }
}