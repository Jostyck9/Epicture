package com.example.epicture.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiClient {

    companion object {
        const val BASE_URL = "https://api.imgur.com/3/"

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //.addConverterFactory(ScalarsConverterFactory.create())
    }
}