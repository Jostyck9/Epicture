package com.example.epicture.Search

import android.util.Log
import com.example.epicture.Model.Gallery
import com.example.epicture.Model.ResponseApi
import com.example.epicture.Network.ApiClient
import com.example.epicture.Network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchModel : SearchContract.Model {

    val TAG = "SearchModel"

    override fun search(
        onFinishedListener: SearchContract.Model.OnFinishedListener,
        toSearch: String,
        pageNo: Int
    ) {
        val apiService = ApiClient.retrofit.create(ApiInterface::class.java)
        val call : Call<ResponseApi<List<Gallery>>> = apiService.gallerySearch(pageNo, "all", "time", toSearch)

        call.enqueue(object : Callback<ResponseApi<List<Gallery>>> {

            override fun onResponse(
                call: Call<ResponseApi<List<Gallery>>>,
                response: Response<ResponseApi<List<Gallery>>>
            ) {
                val responseList : ResponseApi<List<Gallery>>? = response.body()

                if (responseList == null) {
                    Log.e(TAG, "Body null error")
                    Log.e(TAG, "Code : " + response.code())
                    onFinishedListener.onFailure(Throwable("Body null error : " + response.code()))
                } else {
                    if (responseList.success) {
                        onFinishedListener.onFinished(responseList.data)
                    } else {
                        Log.e(TAG, "Error : " + responseList.status)
                        onFinishedListener.onFailure(Throwable("Error : " + responseList.status))
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