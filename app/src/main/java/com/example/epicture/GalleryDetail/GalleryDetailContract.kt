package com.example.epicture.GalleryDetail

import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image

interface GalleryDetailContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(imageList: List<Image>)
            fun onFinishedLike(res : String)
            fun onFailure(t : Throwable)
        }

        fun favoriteGallery(onFinishedListener : OnFinishedListener, id : String)
    }

    interface View {
        fun setGalleryDetail(gallery : Gallery?)
        fun setGalleryToRecyclerView(imageList: List<Image>)
        fun changeLikeColor(isLiked : Boolean)
        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {
        fun onDestroy()
        fun printGallery(gallery : Gallery?)
        fun like(id: String)
    }
}