package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("id")
    val id : String? = null,

    @SerializedName("title")
    val title : String? = null,

    @SerializedName("description")
    val description : String? = null,

    @SerializedName("datetime")
    val datetime : Int? = null,

    @SerializedName("type")
    val type : String? = null,

    @SerializedName("animated")
    val animated : Boolean? = null,

    @SerializedName("width")
    val width : Int? = null,

    @SerializedName("height")
    val height : Int? = null,

    @SerializedName("size")
    val size : Int? = null,

    @SerializedName("views")
    val views : Int? = null,

    //@SerializedName("bandwidth")
    //val bandwidth : Int? = null,

    @SerializedName("vote")
    val vote : Int? = null,

    @SerializedName("favorite")
    val favorite : Boolean? = null,

    @SerializedName("nsfw")
    val nsfw : Boolean? = null,

    @SerializedName("section")
    val section : String? = null,

    @SerializedName("account_url")
    val account_url : String? = null,

    @SerializedName("account_id")
    val account_id : Int? = null,

    @SerializedName("is_ad")
    val is_add : Boolean? = null,

    @SerializedName("in_most_viral")
    val in_most_viral : Boolean? = null,

    @SerializedName("has_sound")
    val has_sound : Boolean? = null,

    @SerializedName("tags")
    val tags : List<Tag>? = null,

    @SerializedName("ad_type")
    val ad_type : Int? = null,

    @SerializedName("ad_url")
    val ad_url : String? = null,

    @SerializedName("edited")
    val edited : String? = null,

    @SerializedName("in_gallery")
    val in_gallery : Boolean? = null,

    @SerializedName("link")
    val link : String? = null,

    @SerializedName("comment_count")
    val comment_count : Int? = null,

    @SerializedName("favorite_count")
    val favorite_count : Int? = null,

    @SerializedName("ups")
    val ups : Int? = null,

    @SerializedName("downs")
    val downs : Int? = null,

    @SerializedName("points")
    val points : Int? = null,

    @SerializedName("score")
    val score : Int? = null
)