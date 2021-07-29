package com.example.tvseries.data.repository

import com.example.tvseries.data.retrofit.ApiInterface
import com.example.tvseries.model.ShowList

interface CallbackSeries {
    fun onSuccessShows(response: ArrayList<ShowList>)
    fun onFailedShows(errorResponse: String)
}

class SeriesRepository(private val api : ApiInterface) {

    /**
     * Function getFriends, to get a list of users
     * Response: FriendsResponse
     */

    suspend fun getSeries(
        callbackSeries: CallbackSeries,
        search: String
    ) {
        val response = api.getSeries(q = search)
        if (response.isSuccessful) {
            callbackSeries.onSuccessShows(response.body()!!)
        } else {
            callbackSeries.onFailedShows("error")
        }

    }
}