package com.jeluchu.rickandmorty

import android.app.Application
import com.jeluchu.rickandmorty.core.di.appModule
import com.jeluchu.rickandmorty.core.di.cacheModule
import com.jeluchu.rickandmorty.core.di.favouriteModule
import com.jeluchu.rickandmorty.core.di.networkModule
import com.jeluchu.rickandmorty.core.di.repositoryModule
import com.jeluchu.rickandmorty.core.di.serviceModule
import com.jeluchu.rickandmorty.core.di.useCaseModule
import com.jeluchu.rickandmorty.core.di.viewModelModule
import com.jeluchu.rickandmorty.core.utils.prefs.DataStoreHelpers.Companion.initDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class RickAndMortyApp : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initDataStore(this)
        startKoin {
            androidContext(this@RickAndMortyApp)
            if (BuildConfig.DEBUG) androidLogger()
            modules(
                appModule,
                cacheModule,
                favouriteModule,
                useCaseModule,
                serviceModule,
                networkModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}