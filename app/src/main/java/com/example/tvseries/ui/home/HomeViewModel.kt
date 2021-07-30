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
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val seriesRepository: SeriesRepository, private val seasonsRepository: SeasonsRepository) : BaseViewModel(application), CallbackSeries, CallbackSeasons {

    var showList = LiveEvent<ArrayList<ShowList>>()
    var showSelected = MutableLiveData<Show>()
    var seasons = LiveEvent<ArrayList<Episode>>()
    val hideProgress = MutableLiveData(false)
    var search: String = ""
    var showFavorites = LiveEvent<Boolean>()

    /**
     * Function that invokes the repository with the parameters requiered, only if the input is valid
     */
    fun search() {
        if (Validator.validateInput(search)) {
            hideProgress.value = true
            viewModelScope.launch(Dispatchers.IO) {
                seriesRepository.getSeries(this@HomeViewModel, search)
            }
        }
    }

    /**
     * use of coroutine to call service getSeries()
     */
    fun getSeries() {
        hideProgress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            seriesRepository.getSeries(this@HomeViewModel, "girls")
        }
    }

    /**
     * Function when click on item of recyclerView
     */
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
//        TODO("Not yet implemented")
    }

    override fun onFailed(errorResponse: String) {
//        TODO("Not yet implemented")
    }

    override fun onSuccessSeasons(response: ArrayList<Episode>) {
        seasons.postValue(response)
    }


}
