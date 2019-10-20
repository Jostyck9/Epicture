package com.example.epicture.Favorites

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epicture.DiscoverList.DiscoverAdapter
import com.example.epicture.GalleryDetail.GalleryDetailActivity
import com.example.epicture.Model.Gallery
import com.example.epicture.R
import com.google.gson.Gson

class FavoritesActivity : Fragment(), FavoritesContract.View {

    private val TAG = "AccountActivity"
    private val favoritePresenter = FavoritesPresenter(this)
    var root : View? = null

    private var favoritesList = mutableListOf<Gallery>()
    private var pageNo = 0
    private var end = false

    private var rvFavoritesList : RecyclerView? = null
    private var favoriteAdapter : FavoritesAdapter? = null
    private var mLayoutManager : GridLayoutManager? = null
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_favorite, container, false)
        initUI()
        setListeners()
        favoritePresenter.getAccountData(pageNo)
        return root
    }

    private fun initUI() {
        rvFavoritesList = root?.findViewById(R.id.rv_image_list)
        favoriteAdapter = FavoritesAdapter(this, favoritesList)

        mLayoutManager = GridLayoutManager(activity, 1)
        rvFavoritesList!!.layoutManager = mLayoutManager
        rvFavoritesList!!.itemAnimator = DefaultItemAnimator()
        rvFavoritesList!!.adapter = favoriteAdapter
    }

    private fun setListeners() {

        rvFavoritesList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = rvFavoritesList!!.childCount
                totalItemCount = mLayoutManager!!.itemCount
                firstVisibleItem = mLayoutManager!!.findFirstVisibleItemPosition()

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && !end) {
                    favoritePresenter.getAccountData(pageNo)
                    loading = true
                }
            }
        })
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setGalleryToRecyclerView(galleryArrayList: List<Gallery>) {
        for (gallery in galleryArrayList) {
            if (gallery.images != null && gallery.images.isNotEmpty())
                favoritesList.add(gallery)
        }
        if (galleryArrayList.isEmpty()) {
            end = true
            return
        }
        favoriteAdapter?.notifyDataSetChanged()
        pageNo++
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable.message)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
    }

    fun onGalleryItemClick(position : Int) {
        // Start activity
        val nextAct = Intent(activity, GalleryDetailActivity::class.java)

        nextAct.putExtra(GalleryDetailActivity.GALLERY_TO_DISPLAY, Gson().toJson(favoritesList[position]))
        startActivity(nextAct)
    }
}