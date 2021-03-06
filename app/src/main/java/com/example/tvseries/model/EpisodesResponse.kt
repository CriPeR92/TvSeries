package com.example.tvseries.model

import com.google.gson.annotations.SerializedName

class Episode(
    @SerializedName("id") var id: Int?,
    @SerializedName("url") var url: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("season") var season: Int?,
    @SerializedName("number") var number: Int?,
    @SerializedName("type") var type: String?,
    @SerializedName("airdate") var airdate: String?,
    @SerializedName("airtime") var airtime: String?,
    @SerializedName("airstamp") var airstamp: String?,
    @SerializedName("runtime") var runtime: Int?,
    @SerializedName("image") var image: Image?,
    @SerializedName("summary") var summary: String?,
    @SerializedName("_links") var _links: Any?
)