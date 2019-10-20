package com.example.epicture.Network

import com.example.epicture.Constants
import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image
import com.example.epicture.Model.ResponseApi
import com.example.epicture.Model.User
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
                @Header("Authorization") idClient : String = "Client-ID " + Constants.clientID
                ) : Call<ResponseApi<List<Gallery>>>

    @GET("account/{username}")
    fun accountBase(@Path("username") username : String,
                   @Header("Authorization") idClient : String = "Client-ID " + Constants.clientID
                   ) : Call<ResponseApi<User>>

    @GET("account/me/images")
    fun getAccountImages(@Header("Authorization") bearer : String = "Bearer " + Constants.accessToken
                        ) : Call<ResponseApi<List<Image>>>

    @GET("account/{username}/gallery_favorites/{page}/{favoritesSort}")
    fun accountGalleryFavorites(@Path("username") username: String,
                                @Path("page") page : Int = 0,
                                @Path("favoritesSort") favoritesSort : String = "newest",
                                @Header("Authorization") idClient : String = "Client-ID " + Constants.clientID
                                ) : Call<ResponseApi<List<Gallery>>>

    @GET("gallery/search/{sort}/{window}/{page}")
    fun gallerySearch(
                      @Path("page") pageNo : Int,
                      @Path("window") window : String,
                      @Path("sort") sort : String,
                      @Query("q") toSearch : String,
                      @Header("Authorization") idClient : String = "Client-ID " + Constants.clientID
                    ) : Call<ResponseApi<List<Gallery>>>
}