package com.example.epicture.DiscoverList

import com.example.epicture.Model.Gallery

import android.util.Log
import com.example.epicture.Model.ResponseApi
import com.example.epicture.Network.ApiClient
import com.example.epicture.Network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverListModel : DiscoverListContract.Model {

    val TAG = "DiscoverListModel"

    override fun getGalleryList(
        onFinishedListener: DiscoverListContract.Model.OnFinishedListener,
        pageNo: Int
    ) {
        val apiService = ApiClient.retrofit.create(ApiInterface::class.java)

        val call : Call<ResponseApi<List<Gallery>>> = apiService.gallery("hot", "viral", "day", pageNo)
        call.enqueue(object : Callback<ResponseApi<List<Gallery>>> {
            override fun onResponse(
                call: Call<ResponseApi<List<Gallery>>>,
                response: Response<ResponseApi<List<Gallery>>>
            ) {
                val galleries : ResponseApi<List<Gallery>>? = response.body()
                if (galleries == null) {
                    Log.e(TAG, "Body null error")
                    Log.e(TAG, "Code : " + response.code())
                    onFinishedListener.onFailure(Throwable("Body null error : " + response.code().toString()))
                } else {
                    if (galleries.success) {
                        Log.d(TAG, "Number of movies received: " + galleries.data.size.toString())
                        onFinishedListener.onFinished(galleries.data)
                    } else {
                        Log.e(TAG, "Error : " + galleries.status)
                        onFinishedListener.onFailure(Throwable("Error : " + galleries.status))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseApi<List<Gallery>>>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })
    }
}