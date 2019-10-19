package com.example.epicture.Account

import com.example.epicture.Model.Image
import com.example.epicture.Model.User

interface AccountContract
{
    interface Model {
        interface OnFinishedListener {
            fun onFinishedAccount(accountUser : User)
            fun onFinishedPosts(postsUser : List<Image>)

            fun onFailure(t : Throwable)
        }

        fun getAccountUser(onFinishedListener : OnFinishedListener)
        fun getPostsUser(onFinishedListener : OnFinishedListener)
    }

    interface View {
        fun showProgress()

        fun hideProgress()

        fun setPostsToRecyclerView(imageList: List<Image>)
        fun setAccountData(accountUser: User)

        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {

        fun onDestroy()
        fun getAccountData()

    }
}