package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class Response<T> (
    @SerializedName("data")
    val data : T? = null,

    @SerializedName("success")
    val success : Boolean? = null,

    @SerializedName("status")
    val status : Int? = null
)