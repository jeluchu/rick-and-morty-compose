package com.jeluchu.rickandmorty.features.characters.state

import androidx.compose.runtime.Immutable
import com.jeluchu.rickandmorty.features.characters.models.Character
import com.jeluchu.rickandmorty.core.ui.states.RemoteScreenState
import com.jeluchu.rickandmorty.features.characters.models.CharacterEntity
import kotlin.collections.emptyList

@Immutable
data class CharactersState(
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true,
    val isLoadingMore: Boolean = false,
    override val error: String? = null,
    override val isLoading: Boolean = true,
    val characters: List<Character> = emptyList(),
    val favorites: List<CharacterEntity> = emptyList()
) : RemoteScreenState() {
    override fun setMessage(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
}
