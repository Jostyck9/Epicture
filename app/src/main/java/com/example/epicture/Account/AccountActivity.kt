package com.example.epicture.Account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.epicture.Model.Image
import com.example.epicture.Model.User

class AccountActivity : Fragment(), AccountContract.View {

    //private val accountPresenter = AccountPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initUI() {
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponseFailure(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAcountData(accountUser: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPostsToRecyclerView(imageList: List<Image>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}