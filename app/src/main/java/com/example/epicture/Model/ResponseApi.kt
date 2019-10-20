package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class ResponseApi<T> (
    @SerializedName("data")
    val data : T,

    @SerializedName("success")
    val success : Boolean,

    @SerializedName("status")
    val status : Int
)