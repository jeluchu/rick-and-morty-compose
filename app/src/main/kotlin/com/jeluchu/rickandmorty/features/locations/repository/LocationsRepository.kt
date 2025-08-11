package com.jeluchu.rickandmorty.features.locations.repository

import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.network.NetworkHandler
import com.jeluchu.rickandmorty.core.network.Resource
import com.jeluchu.rickandmorty.core.utils.LocalShared
import com.jeluchu.rickandmorty.core.utils.isFetchSixHours
import com.jeluchu.rickandmorty.core.utils.network.networkBoundResource
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.getPreference
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.savePreference
import com.jeluchu.rickandmorty.features.locations.models.LocationResult
import com.jeluchu.rickandmorty.features.locations.models.LocationResultEntity.Companion.isNullOrEmpty
import com.jeluchu.rickandmorty.features.locations.repository.local.LocationsDao
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {
    fun getPagedLocations(page: Int): Flow<Resource<Failure, LocationResult>>

    class LocationsRepositoryImpl(
        private val dao: LocationsDao,
        private val api: LocationsService,
        private val networkHandler: NetworkHandler
    ) : LocationsRepository {
        override fun getPagedLocations(page: Int): Flow<Resource<Failure, LocationResult>> =
            networkBoundResource(
                query = { dao.getLocations(page) },
                fetch = { api.getLocations(page).toLocationResult(page) },
                saveFetchResult = { data ->
                    savePreference(LocalShared.CHARACTERS, System.currentTimeMillis())
                    dao.deletePageOfLocations(page)
                    dao.insertLocationsResult(data.toLocationResultEntity())
                },
                shouldFetch = {
                    dao.getLocationsForServerQuery(page).isNullOrEmpty() ||
                            isFetchSixHours(getPreference(LocalShared.CHARACTERS, 0L)) &&
                            networkHandler.isNetworkAvailable()
                },
                dbTransform = { data -> data.toLocationResult(page) }
            )
    }
}