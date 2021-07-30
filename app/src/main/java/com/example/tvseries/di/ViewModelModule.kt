package com.example.tvseries.di

import com.example.tvseries.ui.episode.EpisodeViewModel
import com.example.tvseries.ui.home.HomeViewModel
import com.example.tvseries.ui.show.ShowViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(androidApplication(), get(), get()) }
    viewModel { ShowViewModel(androidApplication()) }
    viewModel { EpisodeViewModel(androidApplication()) }
}