package com.jeluchu.rickandmorty.features.characters.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class CharacterEntity(
    @SerialName("created")
    val created: String? = null,

    @SerialName("episode")
    val episode: List<String>? = null,

    @SerialName("gender")
    val gender: String? = null,

    @PrimaryKey
    @SerialName("id")
    val id: Int,

    @SerialName("image")
    val image: String? = null,

    @SerialName("location")
    val location: LocationEntity? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("origin")
    val origin: OriginEntity? = null,

    @SerialName("species")
    val species: String? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("url")
    val url: String? = null
) {
    fun toCharacter() = Character(
        id = id,
        url = url ?: "",
        type = type ?: "",
        name = name ?: "",
        image = image ?: "",
        gender = gender ?: "",
        status = status ?: "",
        created = created ?: "",
        species = species ?: "",
        episode = episode ?: emptyList(),
        origin = origin?.toOrigin() ?: Origin.empty(),
        location = location?.toLocation() ?: Location.empty()
    )

    companion object {
        fun empty() = CharacterEntity(
            id = 0,
            url = "",
            type = "",
            name = "",
            image = "",
            gender = "",
            status = "",
            created = "",
            species = "",
            episode = emptyList(),
            origin = OriginEntity.empty(),
            location = LocationEntity.empty()
        )
    }
}