package com.example.epicture.Account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.epicture.Model.Image
import com.example.epicture.Model.User
import com.example.epicture.R

class AccountActivity : Fragment(), AccountContract.View {

    private val TAG = "AccountActivity"
    private val accountPresenter = AccountPresenter(this)

    var root : View? = null

    var my_iv_avatar : ImageView? = null
    var my_iv_cover : ImageView? = null
    var my_tv_username : TextView? = null
    var my_tv_points : TextView? = null
    var my_tv_notoriety : TextView? = null
    var my_tv_bio : TextView? = null
    var my_rv_posts : RecyclerView? = null

    private var accountAdapter: AccountAdapter? = null
    private var mLayoutManager: GridLayoutManager? = null
    private var accountPostsList = mutableListOf<Image>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_account, container, false)
        initUI()
        accountPresenter.getAccountData()
        return root
    }

    private fun initUI() {
        my_iv_avatar = root?.findViewById(R.id.avatar_image)
        my_iv_cover = root?.findViewById(R.id.cover_image)
        my_tv_username = root?.findViewById(R.id.text_username)
        my_tv_points = root?.findViewById(R.id.text_points)
        my_tv_notoriety = root?.findViewById(R.id.text_notoriety)
        my_tv_bio = root?.findViewById(R.id.text_about)
        my_rv_posts = root?.findViewById(R.id.rv_posts)

        accountAdapter = AccountAdapter(this, accountPostsList)

        mLayoutManager = GridLayoutManager(activity, 1)
        my_rv_posts?.layoutManager = mLayoutManager
        my_rv_posts?.isNestedScrollingEnabled = false
        my_rv_posts?.itemAnimator = DefaultItemAnimator()
        my_rv_posts?.adapter = accountAdapter
    }

    override fun hideProgress() {
    }

    override fun showProgress() {
    }

    override fun onResponseFailure(throwable: Throwable) {
        Log.e(TAG, throwable?.message)
        Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
    }

    override fun setAccountData(accountUser: User) {
        if (my_iv_avatar != null)
            Glide.with(this).load(accountUser.avatar).into(my_iv_avatar!!)
        if (my_iv_cover != null)
            Glide.with(this).load(accountUser.cover).into(my_iv_cover!!)

        my_tv_username?.text = accountUser.url
        my_tv_points?.text = accountUser.reputation.toString()
        my_tv_notoriety?.text = accountUser.reputation_name
        my_tv_bio?.text = accountUser.bio
    }

    override fun setPostsToRecyclerView(imageList: List<Image>) {
        accountPostsList.addAll(imageList)
        accountAdapter?.notifyDataSetChanged()
    }

}