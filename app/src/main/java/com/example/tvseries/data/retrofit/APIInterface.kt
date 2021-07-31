package com.example.tvseries.data.retrofit

import com.example.tvseries.model.CastCreditsResponse
import com.example.tvseries.model.Episode
import com.example.tvseries.model.PeopleList
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

    @GET("search/people/")
    suspend fun getPeople(
        @Query("q") q: String
    ): Response<ArrayList<PeopleList>>

    @GET("people/{person_id}/castcredits")
    suspend fun getCastCredits(
        @Path(value = "person_id") id: String,
        @Query("embed") embed: String = "show"
    ): Response<ArrayList<CastCreditsResponse>>

}