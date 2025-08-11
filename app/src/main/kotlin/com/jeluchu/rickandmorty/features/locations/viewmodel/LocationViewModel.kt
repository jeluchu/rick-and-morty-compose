package com.jeluchu.rickandmorty.features.locations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.extensions.flowResourceCollector
import com.jeluchu.rickandmorty.features.characters.usecase.GetCharactersUseCase
import com.jeluchu.rickandmorty.features.characters.state.CharactersState
import com.jeluchu.rickandmorty.features.locations.state.LocationsState
import com.jeluchu.rickandmorty.features.locations.usecase.GetLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LocationViewModel(
    private val getLocations: GetLocationUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LocationsState())
    val state: StateFlow<LocationsState> by lazy { _state.asStateFlow() }

    init {
        loadLocations()
    }

    fun loadLocations() = getLocations(GetLocationUseCase.Params(1)).flowResourceCollector(
        scope = viewModelScope,
        initialValue = LocationsState(),
        onLoading = { _state.update { it.copy(isLoading = true) } },
        onSuccess = { response ->

            _state.update {
                it.copy(
                    currentPage = 1,
                    isLoading = false,
                    locations = response?.results.orEmpty(),
                    hasMorePages = response?.info?.next != null,
                )
            }
        },
        onFailure = { failure -> _state.update { it.copy(error = failure.handleFailure(), isLoading = false) } }
    )

    fun loadMoreLocations() {
        val currentState = _state.value
        val nextPage = currentState.currentPage + 1

        getLocations(GetLocationUseCase.Params(nextPage)).flowResourceCollector(
            scope = viewModelScope,
            initialValue = LocationsState(),
            onLoading = { _state.update { it.copy(isLoading = true) } },
            onSuccess = { response ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        currentPage = nextPage,
                        hasMorePages = response?.info?.next != null,
                        locations = currentState.locations + response?.results.orEmpty()
                    )
                }
            },
            onFailure = { failure ->
                _state.update {
                    it.copy(
                        error = failure.handleFailure(),
                        isLoading = false
                    )
                }
            }
        )
    }


/*
    private fun getLocalData() =
        localRepository.arts.flowCollector(viewModelScope) { task ->
            _state.update { it.copy(favorites = task) }
        }

    private fun getCounter() {
        localRepository.artsCountByType(ArtType.picture).flowCollector(viewModelScope) { task ->
            _state.update {
                it.copy(
                    picturesCounter = it.picturesCounter.copy(
                        current = task.toFloat(),
                        icon = R.drawable.ic_deco_picture
                    )
                )
            }
        }

        localRepository.artsCountByType(ArtType.sculpture).flowCollector(viewModelScope) { task ->
            _state.update {
                it.copy(
                    sculptureCounter = it.sculptureCounter.copy(
                        current = task.toFloat(),
                        icon = R.drawable.ic_deco_sculptures
                    )
                )
            }
        }
    }

    fun isSaved(id: String) = state.value.favorites.find { it.filename == id } != null

    fun isAllPicturesSaved() = state.value.favorites.size == state.value.pictures.size

    fun isAllSculptureSaved() = state.value.favorites.size == state.value.sculptures.size

    fun saveOrDelete(item: Art) = viewModelScope.launch(Dispatchers.IO) {
        if (isSaved(item.filename)) delete(item.filename) else insert(item)
    }

    fun saveOrDeleteAllPictures(items: List<Art>) = viewModelScope.launch(Dispatchers.IO) {
        if (isAllPicturesSaved()) deleteAll(items) else insertAll(items)
    }

    fun saveOrDeleteAllSculptures(items: List<Art>) = viewModelScope.launch(Dispatchers.IO) {
        if (isAllSculptureSaved()) deleteAll(items) else insertAll(items)
    }

    fun insert(item: Art) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.insertArt(item.toArtEntity())
    }

    fun delete(id: String) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.deleteFossil(id)
    }

    fun insertAll(item: List<Art>) = viewModelScope.launch(Dispatchers.IO) {
        localRepository.insertArts(item.map { it.toArtEntity() })
    }

    fun deleteAll(item: List<Art>) = viewModelScope.launch(Dispatchers.IO) {
        item.forEach { localRepository.deleteArt(it.filename) }
    }*/
}

fun Failure?.handleFailure() = when (this) {
    is Failure.NetworkConnection -> "Network Connection Failed: $errorMessage"
    is Failure.ServerError -> "Server Failed (Code: $errorCode): $errorMessage"
    is Failure.CustomError -> errorMessage
    is Failure.LegacyError -> errorMessage
    else -> "Unknow Error"
}