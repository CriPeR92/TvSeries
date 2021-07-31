package com.example.tvseries.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvseries.base.LiveEvent
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.data.repository.CallbackSeasons
import com.example.tvseries.data.repository.CallbackSeries
import com.example.tvseries.data.repository.SeasonsRepository
import com.example.tvseries.data.repository.SeriesRepository
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Error
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val seriesRepository: SeriesRepository, private val seasonsRepository: SeasonsRepository) : BaseViewModel(application), CallbackSeries, CallbackSeasons {

    var showList = LiveEvent<ArrayList<ShowList>>()
    var showSelected = MutableLiveData<Show>()
    var seasons = LiveEvent<ArrayList<Episode>>()
    val hideProgress = MutableLiveData(false)
    var search: String = ""
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<Error>()

    fun search() {
        if (Validator.validateInput(search)) {
            hideProgress.value = true
            viewModelScope.launch(Dispatchers.IO) {
                seriesRepository.getSeries(this@HomeViewModel, search)
            }
        }
    }

    fun getSeries() {
        hideProgress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            seriesRepository.getSeries(this@HomeViewModel, "girls")
        }
    }

    fun onClickActionGridAdapter(show: Show) {
        hideProgress.postValue(true)
        showSelected.postValue(show)
        viewModelScope.launch(Dispatchers.IO) {
            seasonsRepository.getSeasons(this@HomeViewModel, show.id.toString())
        }
    }

    fun favorites() {
        showFavorites.postValue(true)
    }

    override fun onSuccessShows(response: ArrayList<ShowList>) {
       showList.postValue(response)
    }

    override fun onFailedShows(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }

    override fun onFailed(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }

    override fun onSuccessSeasons(response: ArrayList<Episode>) {
        seasons.postValue(response)
    }

}
