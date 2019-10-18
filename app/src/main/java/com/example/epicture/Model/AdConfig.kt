package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class AdConfig (
    @SerializedName("safeFlags")
    val safeFlags : MutableList<String>? = null,

    @SerializedName("highRiskFlags")
    val highRiskFlags : MutableList<String>? = null,

    @SerializedName("unsafeFlags")
    val unsafeFlags : MutableList<String>? = null
)