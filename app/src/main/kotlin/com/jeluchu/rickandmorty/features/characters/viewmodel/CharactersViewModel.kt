package com.jeluchu.rickandmorty.features.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeluchu.rickandmorty.core.exception.Failure
import com.jeluchu.rickandmorty.core.extensions.flowCollector
import com.jeluchu.rickandmorty.core.extensions.flowResourceCollector
import com.jeluchu.rickandmorty.core.interactor.UseCase
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.features.characters.usecase.GetCharactersUseCase
import com.jeluchu.rickandmorty.features.characters.state.CharactersState
import com.jeluchu.rickandmorty.features.characters.usecase.DeleteCharactersFavoritesUseCase
import com.jeluchu.rickandmorty.features.characters.usecase.GetCharactersFavoritesUseCase
import com.jeluchu.rickandmorty.features.characters.usecase.InsertCharactersFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val getCharacters: GetCharactersUseCase,
    private val getLocalCharacters: GetCharactersFavoritesUseCase,
    private val insertCharacter: InsertCharactersFavoritesUseCase,
    private val deleteCharacters: DeleteCharactersFavoritesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CharactersState())
    val state: StateFlow<CharactersState> by lazy { _state.asStateFlow() }

    init {
        getLocalData()
        loadCharacters()
    }

    fun loadCharacters() = getCharacters(GetCharactersUseCase.Params(1)).flowResourceCollector(
        scope = viewModelScope,
        initialValue = CharactersState(),
        onLoading = { _state.update { it.copy(isLoading = true) } },
        onSuccess = { response ->

            _state.update {
                it.copy(
                    currentPage = 1,
                    isLoading = false,
                    characters = response?.results.orEmpty(),
                    hasMorePages = response?.info?.next != null,
                )
            }
        },
        onFailure = { failure -> _state.update { it.copy(error = failure.handleFailure(), isLoading = false) } }
    )

    fun loadMoreCharacters() {
        val currentState = _state.value
        val nextPage = currentState.currentPage + 1

        getCharacters(GetCharactersUseCase.Params(nextPage)).flowResourceCollector(
            scope = viewModelScope,
            initialValue = CharactersState(),
            onLoading = { _state.update { it.copy(isLoading = true) } },
            onSuccess = { response ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isLoadingMore = false,
                        currentPage = nextPage,
                        hasMorePages = response?.info?.next != null,
                        characters = currentState.characters + response?.results.orEmpty()
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

    fun insert(character: Character) = insertCharacter(
        InsertCharactersFavoritesUseCase.Params(character)
    ).launchIn(viewModelScope)

    fun delete(character: Character) = deleteCharacters(
        DeleteCharactersFavoritesUseCase.Params(character)
    ).launchIn(viewModelScope)

    private fun getLocalData() =
        getLocalCharacters(UseCase.None()).flowCollector(viewModelScope) { task ->
            _state.update { it.copy(favorites = task) }
        }

    fun isSaved(id: Int) = state.value.favorites.find { it.id == id } != null

    fun saveOrDelete(item: Character) = viewModelScope.launch(Dispatchers.IO) {
        if (isSaved(item.id)) delete(item) else insert(item)
    }
    /*




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