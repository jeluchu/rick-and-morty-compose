package com.jeluchu.rickandmorty.features.characters.repository

import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CharactersService(private val api: HttpClient) {
    suspend fun getCharacters(page: Int): CharacterResultEntity = api.get("character/?page=$page").body()
}