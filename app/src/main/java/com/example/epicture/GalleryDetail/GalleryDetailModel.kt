package com.example.epicture.GalleryDetail

import android.util.Log
import com.example.epicture.Constants
import com.example.epicture.Model.Gallery
import com.example.epicture.Model.ResponseApi
import com.example.epicture.Network.ApiClient
import com.example.epicture.Network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryDetailModel : GalleryDetailContract.Model {
    val TAG = "GalleryDetailModel"

    override fun favoriteGallery(
        onFinishedListener: GalleryDetailContract.Model.OnFinishedListener,
        id: String
    ) {
        val apiService = ApiClient.retrofit.create(ApiInterface::class.java)
        val call : Call<ResponseApi<String>> = apiService.favoriteAlbum(id)

        call.enqueue(object : Callback<ResponseApi<String>> {

            override fun onResponse(
                call: Call<ResponseApi<String>>,
                response: Response<ResponseApi<String>>
            ) {
                val responseRes : ResponseApi<String>? = response.body()

                if (responseRes == null) {
                    Log.e(TAG, "Body null error")
                    Log.e(TAG, "Code : " + response.code())
                    onFinishedListener.onFailure(Throwable("Body null error : " + response.code()))
                } else {
                    if (responseRes.success) {
                        onFinishedListener.onFinishedLike(responseRes.data)
                    } else {
                        Log.e(TAG, "Error : " + responseRes.status)
                        onFinishedListener.onFailure(Throwable("Error : " + responseRes.status))
                    }
                }
            }


            override fun onFailure(call: Call<ResponseApi<String>>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })
    }
}