package com.example.tvseries.ui.show

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.data.repository.SeasonsRepository
import com.example.tvseries.data.repository.SeriesRepository
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowViewModel(application: Application) : BaseViewModel(application) {

    var episodeSelected = MutableLiveData<Episode>()
    var showSelected = MutableLiveData<Show>()

    /**
     * Function when click on item of recyclerView
     */
    fun onClickActionGridAdapter(episode: Episode) {
        episodeSelected.postValue(episode)
    }
}