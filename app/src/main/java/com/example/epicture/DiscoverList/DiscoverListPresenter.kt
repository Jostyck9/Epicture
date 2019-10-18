package com.example.epicture.DiscoverList

import com.example.epicture.Model.Gallery

class DiscoverListPresenter (val discoverListView : DiscoverListContract.View)
    : DiscoverListContract.Presenter, DiscoverListContract.Model.OnFinishedListener
{
    //val discoverListModel : DiscoverListContract.Model = DiscoverListModel()

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFinished(galleryArrayList: List<Gallery>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMoreData(pageNo: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun requestDataFromServer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}