package com.jeluchu.rickandmorty.features.locations.usecase

import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.core.network.Resource
import com.jeluchu.rickandmorty.features.characters.models.CharacterResult
import com.jeluchu.rickandmorty.features.characters.repository.CharactersRepository
import com.jeluchu.rickandmorty.features.locations.models.LocationResult
import com.jeluchu.rickandmorty.features.locations.repository.LocationsRepository
import kotlinx.coroutines.flow.Flow

class GetLocationUseCase(
    private val channelRepository: LocationsRepository
): UseCase<Resource<Failure, LocationResult>, GetLocationUseCase.Params>() {
    override fun run(params: Params?): Flow<Resource<Failure, LocationResult>> = channelRepository.getPagedLocations(params?.page ?: 1)
    data class Params(val page: Int)
}