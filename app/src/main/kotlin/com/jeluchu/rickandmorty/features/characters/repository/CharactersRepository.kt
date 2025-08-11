package com.jeluchu.rickandmorty.features.characters.repository

import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.network.NetworkHandler
import com.jeluchu.rickandmorty.core.network.Resource
import com.jeluchu.rickandmorty.core.utils.LocalShared
import com.jeluchu.rickandmorty.core.utils.isFetchSixHours
import com.jeluchu.rickandmorty.core.utils.network.networkBoundResource
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.getPreference
import com.jeluchu.rickandmorty.core.utils.prefs.PreferencesService.savePreference
import com.jeluchu.rickandmorty.features.characters.models.CharacterResult
import com.jeluchu.rickandmorty.features.characters.models.CharacterResultEntity.Companion.isNullOrEmpty
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getPagedCharacters(page: Int): Flow<Resource<Failure, CharacterResult>>

    class CharactersRepositoryImpl(
        private val dao: CharactersResultDao,
        private val api: CharactersService,
        private val networkHandler: NetworkHandler
    ) : CharactersRepository {
        override fun getPagedCharacters(page: Int): Flow<Resource<Failure, CharacterResult>> =
            networkBoundResource(
                query = { dao.getCharacters(page) },
                fetch = { api.getCharacters(page).toCharacterResult(page) },
                saveFetchResult = { data ->
                    savePreference(LocalShared.CHARACTERS, System.currentTimeMillis())
                    dao.deletePageOfCharacters(page)
                    dao.insertCharactersResult(data.toCharacterResultEntity())
                },
                shouldFetch = {
                    dao.getCharactersForServerQuery(page).isNullOrEmpty() ||
                            isFetchSixHours(getPreference(LocalShared.CHARACTERS, 0L)) &&
                            networkHandler.isNetworkAvailable()
                },
                dbTransform = { data -> data.toCharacterResult(page) }
            )
    }
}