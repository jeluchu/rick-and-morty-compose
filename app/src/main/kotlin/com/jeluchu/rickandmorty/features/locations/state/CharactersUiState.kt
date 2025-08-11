package com.jeluchu.rickandmorty.features.locations.state

import androidx.compose.runtime.Immutable
import com.jeluchu.rickandmorty.core.ui.states.RemoteScreenState
import com.jeluchu.rickandmorty.features.locations.models.Location

@Immutable
data class LocationsState(
    val currentPage: Int = 1,
    val hasMorePages: Boolean = true,
    val isLoadingMore: Boolean = false,
    override val error: String? = null,
    override val isLoading: Boolean = true,
    val locations: List<Location> = emptyList()
) : RemoteScreenState() {
    override fun setMessage(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
}
