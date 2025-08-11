package com.jeluchu.rickandmorty.core.di


import com.jeluchu.rickandmorty.core.network.KtorApi
import com.jeluchu.rickandmorty.core.network.NetworkHandler
import org.koin.dsl.module

val appModule = module {
    single { KtorApi.httpClient }
    single { NetworkHandler(get()) }
}