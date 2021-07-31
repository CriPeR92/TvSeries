package com.example.tvseries.di

import com.example.tvseries.data.repository.CastCreditsRepository
import com.example.tvseries.data.repository.PeopleRepository
import com.example.tvseries.data.repository.SeasonsRepository
import com.example.tvseries.data.repository.SeriesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SeriesRepository(get()) }
    single { SeasonsRepository(get()) }
    single { PeopleRepository(get()) }
    single { CastCreditsRepository(get()) }
}