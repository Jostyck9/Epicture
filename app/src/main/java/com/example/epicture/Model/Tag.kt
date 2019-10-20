package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class Tag (
    @SerializedName("name")
    val name : String? = null,

    @SerializedName("display_name")
    val display_name : String? = null,

    @SerializedName("followers")
    val followers : Int? =  null,

    @SerializedName("total_items")
    val total_items : Int? = null,

    @SerializedName("following")
    val following : Boolean? = null,

    @SerializedName("is_whitelisted")
    val is_whithelisted : Boolean? = null,

    @SerializedName("background_hash")
    val background_hash : String? = null,

    @SerializedName("thumbnail_hash")
    val thumbnail_hash : String? = null,

    @SerializedName("accent")
    val accent : String? = null,

    @SerializedName("background_is_animated")
    val background_is_animated : Boolean? = null,

    @SerializedName("thumbnail_is_animated")
    val thumbnail_is_animated : Boolean? = null,

    @SerializedName("is_promoted")
    val is_promoted : Boolean? = null,

    @SerializedName("description")
    val description : String? = null,

    @SerializedName("logo_hash")
    val logo_hash : String? = null,

    @SerializedName("logo_destination_url")
    val logo_destination_url : String? = null

    //@SerializedName("description_annotations")
    //val description_annotations : String? = null
)