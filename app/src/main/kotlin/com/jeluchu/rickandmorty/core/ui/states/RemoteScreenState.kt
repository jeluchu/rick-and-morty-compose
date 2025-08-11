package com.jeluchu.rickandmorty.core.ui.states

abstract class RemoteScreenState {
    abstract val isLoading: Boolean
    abstract val error: String?

    abstract fun setLoading(value: Boolean): RemoteScreenState
    abstract fun setMessage(value: String?): RemoteScreenState
}