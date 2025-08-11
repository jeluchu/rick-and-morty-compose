package com.jeluchu.rickandmorty.features.characters.models

data class PageResult(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String
) {
    fun toPageResultEntity() = PageResultEntity(
        count = count,
        pages = pages,
        next = next,
        prev = prev
    )

    companion object {
        fun empty() = PageResult(
            count = 0,
            pages = 0,
            next = "",
            prev = ""
        )
    }
}