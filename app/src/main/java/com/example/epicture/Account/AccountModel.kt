package com.example.epicture.Account

import android.util.Log
import com.example.epicture.Constants
import com.example.epicture.Model.Image
import com.example.epicture.Model.ResponseApi
import com.example.epicture.Model.User
import com.example.epicture.Network.ApiClient
import com.example.epicture.Network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountModel : AccountContract.Model {

    val TAG = "AccountModel"

    override fun getAccountUser(onFinishedListener: AccountContract.Model.OnFinishedListener) {
        val apiService = ApiClient.retrofit.create(ApiInterface::class.java)
        val call : Call<ResponseApi<User>> = apiService.accountBase(Constants.username)

        call.enqueue(object : Callback<ResponseApi<User>> {

            override fun onResponse(
                call: Call<ResponseApi<User>>,
                response: Response<ResponseApi<User>>
            ) {
                val responseUser : ResponseApi<User>? = response.body()

                if (responseUser == null) {
                    Log.e(TAG, "Body null error")
                    Log.e(TAG, "Code : " + response.code())
                    onFinishedListener.onFailure(Throwable("Body null error : " + response.code()))
                } else {
                    if (responseUser.success) {
                        onFinishedListener.onFinishedAccount(responseUser.data)
                    } else {
                        Log.e(TAG, "Error : " + responseUser.status)
                        onFinishedListener.onFailure(Throwable("Error : " + responseUser.status))
                    }
                }
            }


            override fun onFailure(call: Call<ResponseApi<User>>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }

        })
    }

    override fun getPostsUser(onFinishedListener: AccountContract.Model.OnFinishedListener) {
        val apiService = ApiClient.retrofit.create(ApiInterface::class.java)
        val call : Call<ResponseApi<List<Image>>> = apiService.getAccountImages()

        call.enqueue(object : Callback<ResponseApi<List<Image>>> {

            override fun onResponse(
                call: Call<ResponseApi<List<Image>>>,
                response: Response<ResponseApi<List<Image>>>
            ) {
                val responseListImage: ResponseApi<List<Image>>? = response.body()

                if (responseListImage == null) {
                    Log.e(TAG, "Body null error")
                    Log.e(TAG, "Code : " + response.code())
                    onFinishedListener.onFailure(Throwable("Body null error : " + response.code()))
                } else {
                    if (responseListImage.success) {
                        onFinishedListener.onFinishedPosts(responseListImage.data)
                    } else {
                        Log.e(TAG, "Error : " + responseListImage.status)
                        onFinishedListener.onFailure(Throwable("Error : " + responseListImage.status))
                    }
                }
            }


            override fun onFailure(call: Call<ResponseApi<List<Image>>>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
                onFinishedListener.onFailure(t)
            }
        })
    }
}