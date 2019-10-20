package com.example.epicture.Search

import com.example.epicture.Model.Gallery

interface SearchContract {
    interface Model {
        interface OnFinishedListener {
            fun onFinished(listGallery : List<Gallery>)
            fun onFailure(t : Throwable)
        }

        fun search(onFinishedListener : OnFinishedListener, toSearch : String, pageNo : Int)
    }

    interface View {
        fun showProgress()
        fun hideProgress()

        fun setGalleryToRecyclerView(imageList: List<Gallery>)
        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {
        fun onDestroy()
        fun search(toSearch: String, pageNo : Int)
    }
}