package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class UserFollow (
    @SerializedName("status")
    val status: Boolean? = null
)