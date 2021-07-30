package com.example.tvseries.ui.favorites

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.base.LiveEvent
import com.example.tvseries.data.repository.CallbackSeasons
import com.example.tvseries.data.repository.SeasonsRepository
import com.example.tvseries.model.Episode
import com.example.tvseries.model.Show
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel (application: Application, private val seasonsRepository: SeasonsRepository) : BaseViewModel(application),
    CallbackSeasons {

    var showSelected = MutableLiveData<Show>()
    var seasons = LiveEvent<ArrayList<Episode>>()
    var remove = LiveEvent<Show>()

    fun removeFavorite(show: Show) {
        remove.postValue(show)
    }

    fun onClickActionGridAdapter(show: Show) {
        showSelected.postValue(show)
        viewModelScope.launch(Dispatchers.IO) {
            seasonsRepository.getSeasons(this@FavoritesViewModel, show.id.toString())
        }
    }

    override fun onSuccessSeasons(response: ArrayList<Episode>) {
        seasons.postValue(response)
    }

    override fun onFailed(errorResponse: String) {
        TODO("Not yet implemented")
    }
}