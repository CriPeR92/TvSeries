package com.example.tvseries.ui.people


import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tvseries.base.LiveEvent
import com.example.tvseries.base.BaseViewModel
import com.example.tvseries.data.repository.*
import com.example.tvseries.model.*
import com.example.tvseries.ui.home.Validator
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleViewModel(application: Application, private val peopleRepository: PeopleRepository, private val castCreditsRepository: CastCreditsRepository) : BaseViewModel(application), CallbackPeople, CallbackCast {

    var peopleList = LiveEvent<ArrayList<PeopleList>>()
    var peopleSelected = MutableLiveData<Person>()
    val hideProgress = MutableLiveData(false)
    var search: String = ""
    var seriesPerson = LiveEvent<ArrayList<CastCreditsResponse>>()
    var showFavorites = LiveEvent<Boolean>()
    var error = LiveEvent<Error>()

    fun search() {
        if (Validator.validateInput(search)) {
            hideProgress.value = true
            viewModelScope.launch(Dispatchers.IO) {
                peopleRepository.getPeople(this@PeopleViewModel, search)
            }
        }
    }

    fun getPeople() {
        hideProgress.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            peopleRepository.getPeople(this@PeopleViewModel, "girls")
        }
    }

    fun onClickActionGridAdapter(person: Person) {
        hideProgress.postValue(true)
        peopleSelected.postValue(person)
        viewModelScope.launch(Dispatchers.IO) {
            castCreditsRepository.getCast(this@PeopleViewModel, person.id.toString())
        }
    }

    override fun onSuccessPeople(response: ArrayList<PeopleList>) {
        peopleList.postValue(response)
    }

    override fun onFailedPeople(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }

    override fun onSuccessCast(response: ArrayList<CastCreditsResponse>) {
        seriesPerson.postValue(response)
    }

    override fun onFailedCast(errorResponse: String) {
        error.postValue(Gson().fromJson(errorResponse, Error::class.java))
    }


}
