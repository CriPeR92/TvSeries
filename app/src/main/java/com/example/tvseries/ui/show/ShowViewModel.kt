package com.example.tvseries.ui.show

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.data.repository.SeasonsRepository
import com.example.tvseries.data.repository.SeriesRepository
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList

class ShowViewModel(application: Application) : BaseViewModel(application) {

    var showList = MutableLiveData<ArrayList<ShowList>>()
    var showSelected = MutableLiveData<Show>()
    var seasons = MutableLiveData<ArrayList<Episode>>()
    val hideProgress = MutableLiveData(0)
    var search: String = ""
}