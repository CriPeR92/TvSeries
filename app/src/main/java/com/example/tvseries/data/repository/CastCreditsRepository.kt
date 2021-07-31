package com.example.tvseries.data.repository

import com.example.tvseries.data.retrofit.ApiInterface
import com.example.tvseries.model.CastCreditsResponse

interface CallbackCast {
    fun onSuccessCast(response: ArrayList<CastCreditsResponse>)
    fun onFailedCast(errorResponse: String)
}

class CastCreditsRepository(private val api: ApiInterface) {

    suspend fun getCast(
        callbackCast: CallbackCast,
        id: String
    ) {
        val response = api.getCastCredits(id = id)
        if (response.isSuccessful) {
            callbackCast.onSuccessCast(response.body()!!)
        } else {
            callbackCast.onFailedCast("error")
        }

    }
}