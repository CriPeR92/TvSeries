package com.example.tvseries

import android.app.Application
import com.example.tvseries.di.apiModule
import com.example.tvseries.di.repositoryModule
import com.example.tvseries.di.retrofitModule
import com.example.tvseries.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TvSeriesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TvSeriesApp)
            modules(
                listOf(
                    retrofitModule,
                    apiModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}