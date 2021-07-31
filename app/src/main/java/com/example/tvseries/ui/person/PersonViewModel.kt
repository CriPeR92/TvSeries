package com.example.tvseries.ui.person

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.base.LiveEvent
import com.example.tvseries.data.repository.CallbackSeasons
import com.example.tvseries.data.repository.SeasonsRepository
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Error
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(application: Application, private val seasonsRepository: SeasonsRepository) :
    BaseViewModel(application),
    CallbackSeasons {

    var showSelected = MutableLiveData<Show>()
    var seasons = LiveEvent<ArrayList<Episode>>()
    var search: String = ""
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<Error>()

    fun onClickActionGridAdapter(show: Show) {
        showSelected.postValue(show)
        viewModelScope.launch(Dispatchers.IO) {
            seasonsRepository.getSeasons(this@PersonViewModel, show.id.toString())
        }
    }

    fun favorites() {
        showFavorites.postValue(true)
    }

    override fun onFailed(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }

    override fun onSuccessSeasons(response: ArrayList<Episode>) {
        seasons.postValue(response)
    }

}
