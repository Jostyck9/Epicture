package com.example.epicture.GalleryDetail

import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image

interface GalleryDetailContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(imageList: List<Image>)
            fun onFailure(t : Throwable)
        }

        //fun getFavoritesUser(onFinishedListener : OnFinishedListener, pageNo : Int)
    }

    interface View {
        fun setGalleryDetail(gallery : Gallery?)
        fun setGalleryToRecyclerView(imageList: List<Image>)
        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {
        fun onDestroy()
        fun printGallery(gallery : Gallery?)
    }
}