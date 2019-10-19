package com.example.epicture.Account

import com.example.epicture.Model.Image
import com.example.epicture.Model.User

class AccountPresenter (var accountView : AccountContract.View?)
: AccountContract.Presenter, AccountContract.Model.OnFinishedListener {
    override fun getAccountData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFailure(t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFinishedAccount(accountUser: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFinishedPosts(postsUser: List<Image>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}