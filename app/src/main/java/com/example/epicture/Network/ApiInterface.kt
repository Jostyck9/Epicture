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
                @Header("Authorization:\\Client-ID\\") idClient : String = "c0de6adf042c785") : Call<ResponseApi<List<Gallery>>>
                //@Query("showViral") showViral : String = "true",
                //@Query("shwMature") showMature : String = "true",
                //@Query("albumPreviews") albumPreviews : String = "true",
}