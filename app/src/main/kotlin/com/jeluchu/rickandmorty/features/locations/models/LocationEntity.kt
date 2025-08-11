package com.jeluchu.rickandmorty.features.locations.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationEntity(
    @SerialName("created")
    val created: String?,

    @SerialName("dimension")
    val dimension: String?,

    @SerialName("id")
    val id: Int?,

    @SerialName("name")
    val name: String?,

    @SerialName("residents")
    val residents: List<String>?,

    @SerialName("type")
    val type: String?,

    @SerialName("url")
    val url: String?
) {
    fun toLocation() = Location(
        created = created.orEmpty(),
        dimension = dimension.orEmpty(),
        id = id ?: 0,
        name = name.orEmpty(),
        residents = residents.orEmpty(),
        type = type.orEmpty(),
        url = url.orEmpty()
    )

    companion object {
        fun empty() = LocationEntity(
            created = "",
            dimension = "",
            id = 0,
            name = "",
            residents = emptyList(),
            type = "",
            url = ""
        )
    }
}