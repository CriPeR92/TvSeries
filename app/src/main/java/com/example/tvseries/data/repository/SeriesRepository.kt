package com.example.tvseries.data.repository

import com.example.tvseries.data.retrofit.ApiInterface
import com.example.tvseries.model.ShowList

interface Callback {
    fun onSuccess(response: ArrayList<ShowList>)
    fun onFailed(errorResponse: String)
}

class SeriesRepository(private val api : ApiInterface) {

    /**
     * Function getFriends, to get a list of users
     * Response: FriendsResponse
     */

    suspend fun getSeries(
        callback: Callback
    ) {
        val response = api.getSeries()
        if (response.isSuccessful) {
            callback.onSuccess(response.body()!!)
        } else {
            callback.onFailed("error")
        }

    }
}