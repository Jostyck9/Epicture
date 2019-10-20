package com.example.epicture.Search

import com.example.epicture.Model.Gallery

class SearchPresenter (var searchView : SearchContract.View?)
    : SearchContract.Presenter, SearchContract.Model.OnFinishedListener {

    private val searchModel : SearchContract.Model = SearchModel()

    override fun search(toSearch: String, pageNo: Int) {
        searchModel.search(this, toSearch, pageNo)
    }

    override fun onDestroy() {
        this.searchView = null
    }

    override fun onFailure(t: Throwable) {
        this.searchView?.onResponseFailure(t)
    }

    override fun onFinished(listGallery: List<Gallery>) {
        this.searchView?.setGalleryToRecyclerView(listGallery)
    }
}