package com.example.epicture.Network

import com.example.epicture.Model.Gallery
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {

    @GET("gallery/{section}/{sort}/{window}/{page}?showViral={showViral}&mature={showMature}&album_previews={albumPreviews}")
    fun gallery(@Path("section") section : String,
                @Path("sort") sort : String,
                @Path("window") window : String,
                @Path("page") pageNo : Int,
                @Path("showViral") showViral : Boolean,
                @Path("shwMature") showMature : Boolean,
                @Path("albumPreviews") albumPreviews : Boolean,
                @Header("Authorization: Client-ID") idClient : String = "c0de6adf042c785") : Call<Response<Gallery>>
}