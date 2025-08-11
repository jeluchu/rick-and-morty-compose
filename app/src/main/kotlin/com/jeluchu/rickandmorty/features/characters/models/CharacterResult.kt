package com.jeluchu.rickandmorty.features.characters.models

data class CharacterResult(
    val currentPage: Int,
    val info: PageResult,
    val results: List<Character>
) {
    fun toCharacterResultEntity() = CharacterResultEntity(
        currentPage = currentPage,
        info = info.toPageResultEntity(),
        results = results.map { it.toCharacterEntity() }
    )
}