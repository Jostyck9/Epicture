package com.example.epicture.GalleryDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image
import com.example.epicture.R
import com.google.gson.Gson

class GalleryDetailActivity : AppCompatActivity(), GalleryDetailContract.View {

    val TAG = "GalleryDerailActivity"
    var galleryDetailPresenter = GalleryDetailPresenter(this)
    companion object {
        var GALLERY_TO_DISPLAY : String = ""
        var ALREADY_LIKED : Boolean = false
    }
    var my_gallery : Gallery? = null

    var myListImage = mutableListOf<Image>()
    var galleryDetailAdapter : GalleryDetailAdapter? = null
    private var mLayoutManager: GridLayoutManager? = null

    var bt_like : ImageButton? = null
    var tv_title : TextView? = null
    var tv_user : TextView? = null
    var tv_ups : TextView? = null
    var tv_downs : TextView? = null
    var tv_description : TextView? = null
    var rv_recycle_view : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_detail)
        GALLERY_TO_DISPLAY = intent.getStringExtra(GALLERY_TO_DISPLAY)
        ALREADY_LIKED = intent.getBooleanExtra("ALREADY_LIKED", false)
        my_gallery = Gson().fromJson(GALLERY_TO_DISPLAY, Gallery::class.java)

        initUi()
        setListener()
        galleryDetailPresenter.printGallery(my_gallery)
    }

    private fun initUi() {
        bt_like = this.findViewById(R.id.im_favorite)
        tv_title = this.findViewById(R.id.tv_title)
        tv_user = this.findViewById(R.id.tv_username)
        tv_ups = this.findViewById(R.id.tv_ups)
        tv_downs = this.findViewById(R.id.tv_down)
        tv_description = this.findViewById(R.id.tv_description)
        rv_recycle_view = this.findViewById(R.id.rv_posts)

        galleryDetailAdapter = GalleryDetailAdapter(this, myListImage)

        mLayoutManager = GridLayoutManager(this, 1)
        rv_recycle_view?.layoutManager = mLayoutManager
        rv_recycle_view?.isNestedScrollingEnabled = false
        rv_recycle_view?.itemAnimator = DefaultItemAnimator()
        rv_recycle_view?.adapter = galleryDetailAdapter

        if (ALREADY_LIKED == true)
            changeLikeColor(true)
    }

    private fun setListener() {
        bt_like?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                likeCallBack(p0)
            }
        })
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable.message)
        Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun setGalleryToRecyclerView(imageList: List<Image>) {
        myListImage.addAll(imageList)
        galleryDetailAdapter?.notifyDataSetChanged()
    }

    override fun setGalleryDetail(gallery: Gallery?) {
        tv_title?.text = gallery?.title
        tv_user?.text = gallery?.account_url
        tv_ups?.text = gallery?.ups.toString()
        tv_downs?.text = gallery?.downs.toString()
        if (gallery == null || (gallery != null && gallery.description == null)) {
            tv_description?.visibility = View.INVISIBLE
        } else {
            tv_description?.text = gallery.description
        }
    }

    override fun changeLikeColor(isLiked : Boolean) {
        if (isLiked) {
            bt_like?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_pink_24dp))
            //bt_like?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        } else {
            bt_like?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp))
            //bt_like?.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLayout))
        }
    }

    fun likeCallBack(view: View?) {
        if (my_gallery != null && my_gallery!!.id != null)
            this.galleryDetailPresenter.like(my_gallery!!.id!!)
    }
}
