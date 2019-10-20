package com.example.epicture.Search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epicture.Favorites.FavoritesAdapter
import com.example.epicture.Favorites.FavoritesPresenter
import com.example.epicture.GalleryDetail.GalleryDetailActivity
import com.example.epicture.Model.Gallery
import com.example.epicture.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_search.*

class SearchActivity : Fragment(), SearchContract.View {
    private val TAG = "SearchActivity"
    private val searchPresenter = SearchPresenter(this)
    var root : View? = null

    private var searchList = mutableListOf<Gallery>()
    private var pageNo = 0
    private var query = ""

    private var tv_msg : TextView? = null
    private var search_bar : TextView? = null
    private var bt_startResearch : ImageButton? = null
    private var rvSearchList : RecyclerView? = null
    private var searchAdapter : SearchAdapter? = null
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
        root = inflater.inflate(R.layout.fragment_search, container, false)
        initUI()
        setListeners()
        return root
    }

    private fun initUI() {
        rvSearchList = root?.findViewById(R.id.rv_image_list)
        searchAdapter = SearchAdapter(this, searchList)

        search_bar = root?.findViewById(R.id.search_bar)
        bt_startResearch = root?.findViewById(R.id.bt_startResearch)

        mLayoutManager = GridLayoutManager(activity, 1)
        rvSearchList!!.layoutManager = mLayoutManager
        rvSearchList!!.itemAnimator = DefaultItemAnimator()
        rvSearchList!!.adapter = searchAdapter
        tv_msg = root?.findViewById(R.id.tv_msg)
        hideMsg()
    }

    private fun setListeners() {

        rvSearchList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = rvSearchList!!.childCount
                totalItemCount = mLayoutManager!!.itemCount
                firstVisibleItem = mLayoutManager!!.findFirstVisibleItemPosition()

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold && searchList.isNotEmpty()) {
                    searchPresenter.search(query, pageNo)
                    loading = true
                }
            }
        })

        bt_startResearch?.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                startResearch(v)
            }

        })
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    fun hideMsg() {
        tv_msg?.visibility = View.INVISIBLE
    }

    fun showMsg() {
        tv_msg?.visibility = View.VISIBLE
    }

    override fun setGalleryToRecyclerView(galleryArrayList: List<Gallery>) {
        if (galleryArrayList.isEmpty())
            showMsg()
        for (gallery in galleryArrayList) {
            if (gallery.images != null && gallery.images.isNotEmpty())
                searchList.add(gallery)
        }
        searchAdapter?.notifyDataSetChanged()
        pageNo++
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable.message)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
    }

    fun startResearch(view: View?) {
        query = search_bar?.text.toString()
        searchList.clear()
        pageNo = 0
        hideMsg()

        searchPresenter.search(query, pageNo)
    }

    fun onGalleryItemClick(position : Int) {
        // Start activity
        if (position >= 0 && position < searchList.size) {
            val nextAct = Intent(activity, GalleryDetailActivity::class.java)
            nextAct.putExtra(GalleryDetailActivity.GALLERY_TO_DISPLAY, Gson().toJson(searchList[position]))
            startActivity(nextAct)
        }
    }
}