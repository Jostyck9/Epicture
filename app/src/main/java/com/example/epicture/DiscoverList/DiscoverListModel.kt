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

        val call : Call<String> = apiService.gallery("hot", "viral", "day", pageNo)
        Log.wtf(TAG, call.request().url().toString())
        Log.wtf(TAG, call.request().headers().toString())
        call.enqueue(object : Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                val galleries : String? = response.body()
                if (galleries == null) {
                    //Log.e(TAG, "Body null error")
                    Log.e(TAG, "Code : " + response.raw())
                    onFinishedListener.onFailure(Throwable("Body null error"))
                } else {
                    onFinishedListener.onFinished(galleries)
                    //if (galleries.success) {
                    //    Log.d(TAG, "Number of movies received: " + galleries.data?.size?.toString())
                    //    onFinishedListener.onFinished(galleries.data)
                    //} else {
                    //    Log.e(TAG, "Error : " + galleries.status)
                    //    onFinishedListener.onFailure(Throwable("Error : " + galleries.status))
                    //}
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })
    }
}