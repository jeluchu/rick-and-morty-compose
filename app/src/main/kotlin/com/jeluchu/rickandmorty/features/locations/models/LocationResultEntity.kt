package com.jeluchu.rickandmorty.features.locations.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity
import com.jeluchu.rickandmorty.features.characters.models.PageResult
import com.jeluchu.rickandmorty.features.characters.models.PageResultEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class LocationResultEntity(
    @PrimaryKey
    val currentPage: Int = 0,

    @SerialName("info")
    val info: PageResultEntity?,

    @SerialName("results")
    val results: List<LocationEntity>?
) {
    fun toLocationResult(page: Int) = LocationResult(
        currentPage = page,
        info = info?.toPageResult() ?: PageResult.empty(),
        results = results?.map { it.toLocation() }.orEmpty()
    )

    companion object {
        fun LocationResultEntity?.isNullOrEmpty() = this == null || this == empty()

        fun empty() = LocationResultEntity(
            currentPage = 0,
            results = emptyList(),
            info = PageResultEntity.empty()
        )
    }
}