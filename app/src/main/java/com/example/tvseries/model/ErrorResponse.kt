package com.example.tvseries.model

import com.google.gson.annotations.SerializedName

class Error(@SerializedName("messages") var data: ArrayList<ErrorMessages>)

class ErrorMessages(
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String,
    @SerializedName("userMessage") var userMessage: String,
    @SerializedName("useApiMessage") var useApiMessage: Boolean,
    @SerializedName("type") var type: String
)