package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class AdConfig (
    @SerializedName("safeFlags")
    val safeFlags : List<String>? = null,

    @SerializedName("highRiskFlags")
    val highRiskFlags : List<String>? = null,

    @SerializedName("unsafeFlags")
    val unsafeFlags : List<String>? = null
)