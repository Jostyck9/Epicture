package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    val id : Int? = null,

    @SerializedName("url")
    val url : String? = null,

    @SerializedName("bio")
    val bio : String? = null,

    @SerializedName("avatar")
    val avatar : String? = null,

    @SerializedName("avatar_name")
    val avatar_name : String? = null,

    @SerializedName("cover")
    val cover : String? = null,

    @SerializedName("cover_name")
    val cover_name : String? = null,

    @SerializedName("reputation")
    val reputation : Int? = null,

    @SerializedName("reputation_name")
    val reputation_name : String? = null,

    @SerializedName("created")
    val created : Int? = null,

    @SerializedName("pro_expiration")
    val pro_expiration : Boolean? = null,

    @SerializedName("user_follow")
    val user_follow : UserFollow? = null,

    @SerializedName("is_blocked")
    val is_blocked : Boolean? = null
)