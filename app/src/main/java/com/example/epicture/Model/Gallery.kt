package com.example.epicture.Model

import com.google.gson.annotations.SerializedName

data class Gallery (
    @SerializedName("id")
    val id : String? = null,

    @SerializedName("title")
    val title : String? = null,

    @SerializedName("description")
    val description : String? = null,

    @SerializedName("datetime")
    val datetime : Int? = null,

    @SerializedName("cover")
    val cover : String? = null,

    @SerializedName("cover_width")
    val cover_width : Int? = null,

    @SerializedName("cover_height")
    val cover_height : Int? = null,

    @SerializedName("account_url")
    val account_url : String? = null,

    @SerializedName("account_id")
    val account_id : Int? = null,

    @SerializedName("privacy")
    val privacy : String? = null,

    @SerializedName("layout")
    val layout : String? = null,

    @SerializedName("views")
    val views : Int? = null,

    @SerializedName("link")
    val link : String? = null,

    @SerializedName("ups")
    val ups : Int? = null,

    @SerializedName("downs")
    val downs : Int? = null,

    @SerializedName("points")
    val points : Int? = null,

    @SerializedName("score")
    val score : Int? = null,

    @SerializedName("is_album")
    val is_album : Boolean? = null,

    @SerializedName("vote")
    val vote : Int? = null,

    @SerializedName("favorite")
    val favorite : Boolean? = null,

    @SerializedName("nsfw")
    val nsfw : Boolean? = null,

    @SerializedName("section")
    val section : String? = null,

    @SerializedName("comment_count")
    val comment_count : Int? = null,

    @SerializedName("favorite_count")
    val favorite_count : Int? = null,

    @SerializedName("topic")
    val topic : String? = null,

    @SerializedName("topic_id")
    val topic_id : Int? = null,

    @SerializedName("images_count")
    val images_count : Int? = null,

    @SerializedName("in_gallery")
    val in_gallery : Boolean? = null,

    @SerializedName("is_ad")
    val is_add : Boolean? = null,

    @SerializedName("tags")
    val tags : MutableList<Tag>? = null,

    @SerializedName("ad_type")
    val ad_type : Int? = null,

    @SerializedName("ad_url")
    val ad_url : String? = null,

    @SerializedName("in_most_viral")
    val in_most_viral : Boolean? = null,

    @SerializedName("include_album_ads")
    val include_album_ads : Boolean? = null,

    @SerializedName("images")
    val images : List<Image>? = null,

    @SerializedName("ad_config")
    val ad_config : AdConfig? = null
)