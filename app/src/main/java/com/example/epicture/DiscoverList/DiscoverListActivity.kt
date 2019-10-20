package com.example.epicture.DiscoverList


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
import com.example.epicture.GalleryDetail.GalleryDetailActivity
import com.example.epicture.Login.LoginActivity
import com.example.epicture.Model.Gallery
import com.example.epicture.R
import com.google.gson.Gson

class DiscoverListActivity : Fragment(), DiscoverListContract.View {
    private val TAG = "DiscoverListActivity"
    private var discoverListPresenter = DiscoverListPresenter(this)
    private var rvDiscoverList: RecyclerView? = null
    private var discoverList = mutableListOf<Gallery>()
    private var discoverAdapter: DiscoverAdapter? = null

    private var pageNo = 1
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0
    private var mLayoutManager: GridLayoutManager? = null

    private var root : View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_discover, container, false)
        initUI()
        setListeners()
        discoverListPresenter.getMoreData(1)
        return (root)
    }

    private fun initUI() {
        rvDiscoverList = root?.findViewById(R.id.rv_image_list)
        discoverAdapter = DiscoverAdapter(this, discoverList)

        mLayoutManager = GridLayoutManager(activity, 1)
        rvDiscoverList!!.layoutManager = mLayoutManager
        rvDiscoverList!!.itemAnimator = DefaultItemAnimator()
        rvDiscoverList!!.adapter = discoverAdapter
    }

    private fun setListeners() {

        rvDiscoverList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = rvDiscoverList!!.childCount
                totalItemCount = mLayoutManager!!.itemCount
                firstVisibleItem = mLayoutManager!!.findFirstVisibleItemPosition()

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                    discoverListPresenter.getMoreData(pageNo)
                    loading = true
                }
            }
        })
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setDataToRecyclerView(discoverArrayList: List<Gallery>) {
        for (gallery in discoverArrayList) {
            if (gallery.images != null && gallery.images.isNotEmpty())
                discoverList.add(gallery)
        }
        discoverAdapter?.notifyDataSetChanged()
        pageNo++
    }


    override fun onResponseFailure(throwable: Throwable) {

        Log.e(TAG, throwable.message)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        discoverListPresenter.onDestroy()
    }

    fun onGalleryItemClick(position : Int) {
        // Start activity
        val nextAct = Intent(activity, GalleryDetailActivity::class.java)

        nextAct.putExtra(GalleryDetailActivity.GALLERY_TO_DISPLAY, Gson().toJson(discoverList[position]))
        startActivity(nextAct)
    }
}