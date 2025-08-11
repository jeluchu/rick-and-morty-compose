package com.jeluchu.rickandmorty.features.characters.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageResultEntity(
    @SerialName("count")
    val count: Int,

    @SerialName("pages")
    val pages: Int,

    @SerialName("next")
    val next: String?,

    @SerialName("prev")
    val prev: String?
) {
    fun toPageResult() = PageResult(
        count = count,
        pages = pages,
        next = next.orEmpty(),
        prev = prev.orEmpty()
    )

    companion object {
        fun empty() = PageResultEntity(
            count = 0,
            pages = 0,
            next = null,
            prev = null
        )
    }
}