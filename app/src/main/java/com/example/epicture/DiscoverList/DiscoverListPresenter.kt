package com.example.epicture.DiscoverList

import android.widget.Toast
import com.example.epicture.Model.Gallery

class DiscoverListPresenter (var discoverListView : DiscoverListContract.View?)
    : DiscoverListContract.Presenter, DiscoverListContract.Model.OnFinishedListener
{
    val discoverListModel : DiscoverListContract.Model = DiscoverListModel()

    override fun onDestroy() {
        this.discoverListView = null
    }

    override fun onFailure(t: Throwable) {
        this.discoverListView?.onResponseFailure(t)
    }

    override fun onFinished(galleryArrayList: List<Gallery>) {
        this.discoverListView?.setDataToRecyclerView(galleryArrayList)
    }

    override fun getMoreData(pageNo: Int) {
        discoverListModel.getGalleryList(this, pageNo)
    }
}