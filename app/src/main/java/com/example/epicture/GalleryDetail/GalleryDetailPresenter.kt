package com.example.epicture.GalleryDetail

import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image

class GalleryDetailPresenter (var galleryDetailView : GalleryDetailContract.View?):
    GalleryDetailContract.Presenter,
    GalleryDetailContract.Model.OnFinishedListener
{

    private val detailModel : GalleryDetailContract.Model = GalleryDetailModel()

    override fun printGallery(gallery : Gallery?) {
        this.galleryDetailView?.setGalleryDetail(gallery)
        if (gallery != null)
            onFinished(gallery.images)
    }

    override fun like(id : String) {
        if (id.isEmpty())
            return
        detailModel.favoriteGallery(this, id)
    }

    override fun onDestroy() {
        this.galleryDetailView = null
    }

    override fun onFailure(t: Throwable) {
        this.galleryDetailView?.onResponseFailure(t)
    }

    override fun onFinished(imageList: List<Image>) {
        this.galleryDetailView?.setGalleryToRecyclerView(imageList)
    }

    override fun onFinishedLike(res: String) {
        if (res == "favorited")
            this.galleryDetailView?.changeLikeColor(true)
        else
            this.galleryDetailView?.changeLikeColor(false)
    }
}