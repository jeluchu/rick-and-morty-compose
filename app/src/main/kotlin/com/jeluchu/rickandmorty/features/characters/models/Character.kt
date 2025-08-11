package com.jeluchu.rickandmorty.features.characters.models

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    fun toCharacterEntity() = CharacterEntity(
        id = id,
        url = url,
        type = type,
        name = name,
        image = image,
        gender = gender,
        status = status,
        created = created,
        species = species,
        episode = episode,
        origin = origin.toOriginEntity(),
        location = location.toLocationEntity()
    )

    companion object {
        fun empty() = Character(
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
            origin = Origin.empty(),
            location = Location.empty()
        )
    }
}