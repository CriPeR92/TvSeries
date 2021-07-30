package com.example.tvseries.ui.show

import android.app.Application
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.base.LiveEvent
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show

class ShowViewModel(application: Application) : BaseViewModel(application) {

    var episodeSelected = LiveEvent<Episode>()
    var showSelected = LiveEvent<Show>()
    var addFavorites = LiveEvent<Boolean>()

    /**
     * Function when click on item of recyclerView
     */
    fun onClickActionGridAdapter(episode: Episode) {
        episodeSelected.postValue(episode)
    }

    fun addFavorites() {
        addFavorites.postValue(true)
    }
}