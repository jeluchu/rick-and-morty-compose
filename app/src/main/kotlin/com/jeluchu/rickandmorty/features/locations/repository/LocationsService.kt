package com.jeluchu.rickandmorty.features.locations.repository

import com.jeluchu.rickandmorty.features.locations.models.LocationResultEntity
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationsService(private val api: HttpClient) {
    suspend fun getLocations(page: Int): LocationResultEntity = api.get("location/?page=$page").body()
}