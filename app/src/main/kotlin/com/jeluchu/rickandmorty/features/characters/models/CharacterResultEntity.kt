package com.jeluchu.rickandmorty.features.characters.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class CharacterResultEntity(
    @PrimaryKey
    val currentPage: Int = 0,

    @SerialName("info")
    val info: PageResultEntity? = null,

    @SerialName("results")
    val results: List<CharacterEntity>? = null
) {
    fun toCharacterResult(page: Int) = CharacterResult(
        currentPage = page,
        info = info?.toPageResult() ?: PageResult.empty(),
        results = results?.map { it.toCharacter() }.orEmpty()
    )

    companion object {
        fun CharacterResultEntity?.isNullOrEmpty() = this == null || this == empty()

        fun empty() = CharacterResultEntity(
            currentPage = 0,
            info = PageResultEntity.empty(),
            results = emptyList()
        )
    }
}