package com.jeluchu.rickandmorty.features.locations.models

import com.jeluchu.rickandmorty.features.characters.models.PageResult
import com.jeluchu.rickandmorty.features.characters.models.PageResultEntity

data class LocationResult(
    val currentPage: Int,
    val info: PageResult,
    val results: List<Location>
)  {
    fun toLocationResultEntity() = LocationResultEntity(
        currentPage = currentPage,
        info = info.toPageResultEntity(),
        results = results.map { it.toLocationEntity() }
    )

    companion object {
        fun empty() = LocationResultEntity(
            currentPage = 0,
            results = emptyList(),
            info = PageResultEntity.empty()
        )
    }
}