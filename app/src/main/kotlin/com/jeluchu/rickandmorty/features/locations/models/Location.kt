package com.jeluchu.rickandmorty.features.locations.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("created")
    val created: String,

    @SerialName("dimension")
    val dimension: String,

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("residents")
    val residents: List<String>,

    @SerialName("type")
    val type: String,

    @SerialName("url")
    val url: String
) {
    fun toLocationEntity() = LocationEntity(
        id = id,
        url = url,
        name = name,
        type = type,
        created = created,
        dimension = dimension,
        residents = residents
    )
}