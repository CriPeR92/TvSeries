package com.example.tvseries.data.repository

import com.example.tvseries.data.retrofit.ApiInterface
import com.example.tvseries.model.PeopleList

interface CallbackPeople {
    fun onSuccessPeople(response: ArrayList<PeopleList>)
    fun onFailedPeople(errorResponse: String)
}

class PeopleRepository(private val api: ApiInterface) {

    suspend fun getPeople(
        callbackPeople: CallbackPeople,
        search: String
    ) {
        val response = api.getPeople(q = search)
        if (response.isSuccessful) {
            callbackPeople.onSuccessPeople(response.body()!!)
        } else {
            callbackPeople.onFailedPeople("error")
        }

    }
}