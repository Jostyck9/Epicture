package com.example.epicture.DiscoverList


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epicture.GridSpacingItemDecoration
import com.example.epicture.GridSpacingItemDecoration.Companion.dpToPx
import com.example.epicture.Model.Gallery
import com.example.epicture.R
import kotlinx.android.synthetic.main.fragment_discover.*
import java.util.ArrayList

class DiscoverListActivity : Fragment(), DiscoverListContract.View {
    private val TAG = "DiscoverListActivity"
    private var discoverListPresenter = DiscoverListPresenter(this)
    private var rvDiscoverList: RecyclerView? = null
    private var discoverList = mutableListOf<Gallery>()
    private var discoverAdapter: DiscoverAdapter? = null
    private var pbLoading: ProgressBar? = null
    //private var fabFilter: FloatingActionButton? = null
    //private var tvEmptyView: TextView? = null

    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    internal var firstVisibleItem: Int = 0
    internal var visibleItemCount: Int = 0
    internal var totalItemCount: Int = 0
    private var mLayoutManager: GridLayoutManager? = null

    var root : View? = null

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

    /**
     * This method will initialize the UI components
     */
    private fun initUI() {
        rvDiscoverList = root?.findViewById(R.id.rv_image_list)
        discoverAdapter = DiscoverAdapter(this, discoverList)

        mLayoutManager = GridLayoutManager(activity, 2)
        rvDiscoverList!!.layoutManager = mLayoutManager
        rvDiscoverList!!.addItemDecoration(
            GridSpacingItemDecoration(2, dpToPx(activity!!.applicationContext, 10),true)
        )
        rvDiscoverList!!.itemAnimator = DefaultItemAnimator()
        rvDiscoverList!!.adapter = discoverAdapter

        pbLoading = root?.findViewById(R.id.pb_loading)
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
                    discoverListPresenter!!.getMoreData(1)
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
        discoverList.addAll(discoverArrayList)
        discoverAdapter?.notifyDataSetChanged()
    }


    override fun onResponseFailure(throwable: Throwable) {

        Log.e(TAG, throwable.message)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        discoverListPresenter.onDestroy()
    }
}