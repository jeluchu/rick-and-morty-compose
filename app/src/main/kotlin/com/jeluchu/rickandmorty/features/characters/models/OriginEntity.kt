package com.jeluchu.rickandmorty.features.characters.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginEntity(
    @SerialName("name")
    val name: String? = null,

    @SerialName("url")
    val url: String? = null
) {
    fun toOrigin() = Origin(
        name = name ?: "",
        url = url ?: ""
    )

    companion object {
        fun empty() = OriginEntity(
            name = "",
            url = ""
        )
    }
}