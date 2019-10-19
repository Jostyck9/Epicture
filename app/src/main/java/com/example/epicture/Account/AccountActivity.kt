package com.example.epicture.Account

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epicture.Constants
import com.example.epicture.DiscoverList.DiscoverAdapter
import com.example.epicture.DiscoverList.DiscoverListPresenter
import com.example.epicture.GridSpacingItemDecoration
import com.example.epicture.Model.Image
import com.example.epicture.Model.User
import com.example.epicture.R
import com.example.epicture.ui.account.AccountViewModel
import kotlinx.android.synthetic.main.fragment_account.*

class AccountActivity : Fragment(), AccountContract.View {

    //private val accountPresenter = AccountPresenter(this)

    private val TAG = "AccountActivity"
    private var accountListPresenter = AccountPresenter(this)
    private var rvAccountList: RecyclerView? = null
    private var accountList = mutableListOf<Image>()
    private var accountAdapter: AccountAdapter? = null

    private var avatar: TextView? = null
    private var cover: TextView? = null
    private var username: TextView? = null
    private var points: TextView? = null
    private var notoriety: TextView? = null
    private var about: TextView? = null
    private var userinfo: User? = null

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
        accountListPresenter.getAccountData()
        return (root)
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initUI() {
        rvAccountList = root?.findViewById(R.id.rv_image_list)
        accountAdapter = AccountAdapter(this, accountList)

        mLayoutManager = GridLayoutManager(activity, 1)
        rvAccountList!!.layoutManager = mLayoutManager
        rvAccountList!!.addItemDecoration(
            GridSpacingItemDecoration(2, GridSpacingItemDecoration.dpToPx(activity!!.applicationContext, 10),true)
        )
        rvAccountList!!.itemAnimator = DefaultItemAnimator()
        rvAccountList!!.adapter = accountAdapter
    }

    private fun setListeners() {

        rvAccountList!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = rvAccountList!!.childCount
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
                    accountListPresenter!!.getAccountData()
                    loading = true
                }
            }
        })
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable.message)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun setAccountData(accountUser: User) {
        avatar = root?.findViewById(R.id.avatar_image)
        Glide.with(activity).load(accountUser.avatar).into(avatar)
        cover = root?.findViewById(R.id.cover_image)
        Glide.with(activity).load(accountUser.avatar).into(avatar)
        username = root?.findViewById(R.id.text_username)
        username?.text = accountUser.url
        points = root?.findViewById(R.id.text_points)
        points?.text = accountUser.reputation.toString()
        notoriety = root?.findViewById(R.id.text_notoriety)
        notoriety?.text = accountUser.reputation_name
        about = root?.findViewById(R.id.text_about)
        about?.text = accountUser.bio
    }

    override fun setPostsToRecyclerView(imageList: List<Image>) {
        accountList.addAll(imageList)
        accountAdapter?.notifyDataSetChanged()
    }
}