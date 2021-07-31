package com.example.tvseries.model

import com.google.gson.annotations.SerializedName

class ShowList(
    @SerializedName("score") var score: Float?,
    @SerializedName("show") var show: Show?,
)

class Image(
    @SerializedName("medium") var medium: String?,
    @SerializedName("original") var original: String?,
)

class Schedule(
    @SerializedName("time") var time: String?,
    @SerializedName("days") var days: ArrayList<String>?,
)


class Show(
    @SerializedName("id") var id: Int?,
    @SerializedName("url") var url: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("language") var language: String?,
    @SerializedName("genres") var genres: ArrayList<String>?,
    @SerializedName("status") var status: String?,
    @SerializedName("runtime") var runtime: Int?,
    @SerializedName("averageRuntime") var averageRuntime: Int?,
    @SerializedName("premiered") var premiered: String?,
    @SerializedName("officialSite") var officialSite: String?,
    @SerializedName("schedule") var schedule: Schedule?,
    @SerializedName("rating") var rating: Any?,
    @SerializedName("weight") var weight: Int?,
    @SerializedName("network") var network: Any?,
    @SerializedName("webChannel") var webChannel: Any?,
    @SerializedName("dvdCountry") var dvdCountry: Any?,
    @SerializedName("externals") var externals: Any?,
    @SerializedName("image") var image: Image?,
    @SerializedName("summary") var summary: String?,
    @SerializedName("updated") var updated: Int?,
    @SerializedName("_links") var _links: Any?,
)

