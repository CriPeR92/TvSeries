package com.example.tvseries.ui.home

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.data.repository.Callback
import com.example.tvseries.data.repository.SeriesRepository
import com.example.tvseries.model.Show
import com.example.tvseries.model.ShowList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application, private val seriesRepository: SeriesRepository) : BaseViewModel(application), Callback {

    var friendSelected = MutableLiveData<Show>()
    val hideProgress = MutableLiveData(0)

    /**
     * use of coroutine to call service getFriends()
     */
    fun getSeries() {
        hideProgress.postValue(1)
        viewModelScope.launch(Dispatchers.IO) {
            seriesRepository.getSeries(this@HomeViewModel)
        }
    }

    /**
     * Function when click on item of recyclerView
     */
    fun onClickActionGridAdapter(show: Show) {
        friendSelected.postValue(show)
    }

    override fun onSuccess(response: ArrayList<ShowList>) {
//        TODO("Not yet implemented")
    }

    override fun onFailed(errorResponse: String) {
//        TODO("Not yet implemented")
    }


}
