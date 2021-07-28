package com.example.tvseries.data.retrofit

import com.example.tvseries.model.ShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/shows/")
    suspend fun getSeries(
        @Query("q") q: String = "girls"
    ): Response<ArrayList<ShowList>>
}