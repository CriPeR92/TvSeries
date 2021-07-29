package com.example.tvseries.data.repository

import com.example.tvseries.data.retrofit.ApiInterface
import com.example.tvseries.model.Episode

interface CallbackSeasons {
    fun onSuccessSeasons(response: ArrayList<Episode>)
    fun onFailed(errorResponse: String)
}

class SeasonsRepository(private val api : ApiInterface) {

    /**
     * Function getFriends, to get a list of users
     * Response: FriendsResponse
     */

    suspend fun getSeasons(
        callbackSeasons: CallbackSeasons,
        id: String
    ) {
        val response = api.getEpisodes(id = id)
        if (response.isSuccessful) {
            callbackSeasons.onSuccessSeasons(response.body()!!)
        } else {
            callbackSeasons.onFailed("error")
        }

    }
}