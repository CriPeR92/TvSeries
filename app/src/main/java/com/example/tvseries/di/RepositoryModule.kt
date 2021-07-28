package com.example.tvseries.di

import com.example.tvseries.data.repository.SeriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SeriesRepository(get()) }
}