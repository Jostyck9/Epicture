package com.example.epicture.DiscoverList

import com.example.epicture.Model.Gallery

interface DiscoverListContract {

    interface Model {
        interface OnFinishedListener {
            fun onFinished(galleryArrayList : List<Gallery>)

            fun onFailure(t : Throwable)
        }

        fun getGalleryList(onFinishedListener : OnFinishedListener, pageNo: Int)
    }

    interface View {
        fun showProgress()

        fun hideProgress()

        fun setDataToRecyclerView(galleryArrayList: List<Gallery>)

        fun onResponseFailure(throwable: Throwable)
    }

    interface Presenter {

        fun onDestroy()

        fun getMoreData(pageNo: Int)

    }
}