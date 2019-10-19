package com.example.epicture.Favorites

import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image
import com.example.epicture.Model.User

interface FavoritesContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(listGallery : List<Gallery>)
            fun onFailure(t : Throwable)
        }

        fun getFavoritesUser(onFinishedListener : OnFinishedListener, pageNo : Int)
    }

    interface View {
        fun showProgress()
        fun hideProgress()

        fun setGalleryToRecyclerView(imageList: List<Gallery>)
        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {
        fun onDestroy()
        fun getAccountData(pageNo : Int)
    }
}