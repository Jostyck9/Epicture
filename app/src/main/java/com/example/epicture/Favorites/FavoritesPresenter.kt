package com.example.epicture.Favorites

import com.example.epicture.Model.Gallery

class FavoritesPresenter (var favoriteView : FavoritesContract.View?)
    : FavoritesContract.Presenter, FavoritesContract.Model.OnFinishedListener {

    private val favoritesModel : FavoritesContract.Model = FavoritesModel()

    override fun getAccountData(pageNo: Int) {
        favoritesModel.getFavoritesUser(this, pageNo)
    }

    override fun onDestroy() {
        this.favoriteView = null
    }

    override fun onFailure(t: Throwable) {
        this.favoriteView?.onResponseFailure(t)
    }

    override fun onFinished(listGallery: List<Gallery>) {
        this.favoriteView?.setGalleryToRecyclerView(listGallery)
    }
}