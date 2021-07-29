package com.example.tvseries.data.retrofit

import com.example.tvseries.model.Episode
import com.example.tvseries.model.ShowList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("search/shows/")
    suspend fun getSeries(
        @Query("q") q: String
    ): Response<ArrayList<ShowList>>


    @GET("shows/{show_id}/episodes")
    suspend fun getEpisodes(
        @Path(value = "show_id") id: String
    ): Response<ArrayList<Episode>>
}