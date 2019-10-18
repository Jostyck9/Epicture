package com.example.epicture.Network

import com.example.epicture.Model.Gallery
import com.example.epicture.Model.ResponseApi
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("gallery/{section}/{sort}/{window}/{page}")
    fun gallery(@Path("section") section : String,
                @Path("sort") sort : String,
                @Path("window") window : String,
                @Path("page") pageNo : Int,
                @Query("showViral") showViral : String = "true",
                @Query("mature") showMature : String = "true",
                @Query("album_previews") albumPreviews : String = "false",
                @Header("Authorization") idClient : String = "Client-ID c0de6adf042c785"
                ) : Call<String>
}