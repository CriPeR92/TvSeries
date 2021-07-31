package com.example.tvseries.model

import com.google.gson.annotations.SerializedName

class PeopleList(
    @SerializedName("score") var score: Float?,
    @SerializedName("person") var person: Person?,
)

class Person(
    @SerializedName("id") var id: Int?,
    @SerializedName("url") var url: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("country") var country: Any?,
    @SerializedName("birthday") var birthday: Any?,
    @SerializedName("deathday") var deathday: Any?,
    @SerializedName("gender") var gender: String?,
    @SerializedName("image") var image: Image?,
    @SerializedName("_links") var _links: Any?,
)

class CastCreditsResponse(
    @SerializedName("self") var self: Boolean?,
    @SerializedName("voice") var voice: Boolean?,
    @SerializedName("_links") var _links: Any?,
    @SerializedName("_embedded") var _embedded: Embedded?,
)

class Embedded(
    @SerializedName("show") var show: Show?,
)

