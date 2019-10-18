package com.example.epicture.DiscoverList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.epicture.Model.Gallery
import com.example.epicture.Model.Image
import java.util.ArrayList

import com.example.epicture.Constants.Companion.ACTION_DISCOVER_FILTER
import com.example.epicture.Constants.Companion.KEY_DISCOVER_ID
import com.example.epicture.Constants.Companion.KEY_RELEASE_FROM
import com.example.epicture.Constants.Companion.KEY_RELEASE_TO
import com.example.epicture.GridSpacingItemDecoration
import com.example.epicture.GridSpacingItemDecoration.Companion.dpToPx

class DiscoverListActivity : AppCompatActivity(), DiscoverListContract.View {
    //private val TAG = "DiscoverListActivity"
    private var discoverListPresenter: DiscoverListPresenter? = null
    private var rvDiscoverList: RecyclerView? = null
    private var discoverList: MutableList<Image>? = null
    private var discoverAdapter: DiscoverAdapter? = null
    private var pbLoading: ProgressBar? = null
    //private var fabFilter: FloatingActionButton? = null
    //private var tvEmptyView: TextView? = null

    private var pageNo = 1

    //Constants for load more
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 5
    internal var firstVisibleItem: Int = 0, intenal var visibleItemCount: Int = 0, internal var totalItemCount: Int = 0
    private var mLayoutManager: GridLayoutManager? = null

    // Constants for filter functionality
    private var fromReleaseFilter: String? = ""
    private var toReleaseFilter: String? = ""

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.fragment_discover)
//        supportActionBar.setTitle(getString(R.string.most_popular_discover))
        initUI()
        setListeners()
        //Initializing presenter
        discoverListPresenter = DiscoverListPresenter(this)
        discoverListPresenter!!.requestDataFromServer()
    }

    /**
     * This method will initialize the UI components
     */
    private fun initUI() {

        rvDiscoverList = findViewById(R.id.rv_image_list)

        discoverList = ArrayList<Image>()
        discoverAdapter = DiscoverAdapter(this, discoverList)

        mLayoutManager = GridLayoutManager(this, 2)
        rvDiscoverList!!.setLayoutManager(mLayoutManager)
        rvDiscoverList!!.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                dpToPx(this, 10),
                true
            )
        )
        rvDiscoverList!!.setItemAnimator(DefaultItemAnimator())
        rvDiscoverList!!.setAdapter(discoverAdapter)

        pbLoading = findViewById(R.id.pb_loading)

        //fabFilter = findViewById(R.id.fab_filter)

        //tvEmptyView = findViewById(R.id.tv_empty_view)
    }

    /**
     * This function will contain listeners for all views.
     */
    private fun setListeners() {

        rvDiscoverList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = rvDiscoverList!!.getChildCount()
                totalItemCount = mLayoutManager!!.getItemCount()
                firstVisibleItem = mLayoutManager!!.findFirstVisibleItemPosition()

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false
                        previousTotal = totalItemCount
                    }
                }
                if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                    discoverListPresenter!!.getMoreData(pageNo)
                    loading = true
                }

                // Hide and show Filter button
                if (dy > 0 && fabFilter!!.getVisibility() === View.VISIBLE) {
                    fabFilter!!.hide()
                } else if (dy < 0 && fabFilter!!.getVisibility() !== View.VISIBLE) {
                    fabFilter!!.show()
                }
            }
        })

        fabFilter!!.setOnClickListener(View.OnClickListener {
            // Going to filter screen
            val discoverFilterIntent = Intent(this@DiscoverListActivity, DiscoverFilterActivity::class.java)
            discoverFilterIntent.putExtra(KEY_RELEASE_FROM, fromReleaseFilter)
            discoverFilterIntent.putExtra(KEY_RELEASE_TO, toReleaseFilter)
            startActivityForResult(discoverFilterIntent, ACTION_DISCOVER_FILTER)
        })

    }

    protected fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {

        if (requestCode == ACTION_DISCOVER_FILTER) {
            if (resultCode == Activity.RESULT_OK) {
                // Checking if there is any data to filter
                fromReleaseFilter = data.getStringExtra(KEY_RELEASE_FROM)
                toReleaseFilter = data.getStringExtra(KEY_RELEASE_TO)

                discoversAdapter!!.setFilterParameter(fromReleaseFilter, toReleaseFilter)
                discoversAdapter!!.getFilter().filter("")
            }
        }
    }

    override fun showProgress() {

        pbLoading!!.visibility = View.VISIBLE
    }

    override fun hideProgress() {

        pbLoading!!.visibility = View.GONE
    }

    override fun setDataToRecyclerView(discoverArrayList: List<Gallery>) {

        discoversList!!.addAll(discoverArrayList)
        discoversAdapter!!.notifyDataSetChanged()

        // This will help us to fetch data from next page no.
        pageNo++
    }


    override fun onResponseFailure(throwable: Throwable) {

        Log.e(TAG, throwable.message)
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show()
    }

    protected fun onDestroy() {
        super.onDestroy()
        discoverListPresenter!!.onDestroy()
    }

    fun onDiscoverItemClick(position: Int) {

        if (position == -1) {
            return
        }
        val detailIntent = Intent(this, DiscoverDetailsActivity::class.java)
        detailIntent.putExtra(KEY_DISCOVER_ID, discoversList!![position].getId())
        startActivity(detailIntent)
    }

    fun showEmptyView() {

        rvDiscoverList!!.setVisibility(View.GONE)
        tvEmptyView!!.visibility = View.VISIBLE

    }

    fun hideEmptyView() {
        rvDiscoverList!!.setVisibility(View.VISIBLE)
        tvEmptyView!!.visibility = View.GONE
    }
}