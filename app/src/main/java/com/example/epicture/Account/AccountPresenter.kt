package com.example.epicture.Account

import com.example.epicture.Model.Image
import com.example.epicture.Model.User

class AccountPresenter (var accountView : AccountContract.View?)
: AccountContract.Presenter, AccountContract.Model.OnFinishedListener {

    private val accountModel : AccountContract.Model = AccountModel()

    override fun getAccountData() {
        accountModel.getAccountUser(this)
        accountModel.getPostsUser(this)
    }

    override fun onDestroy() {
        this.accountView = null
    }

    override fun onFailure(t: Throwable) {
        this.accountView?.onResponseFailure(t)
    }

    override fun onFinishedAccount(accountUser: User) {
        this.accountView?.setAccountData(accountUser)
    }

    override fun onFinishedPosts(postsUser: List<Image>) {
        this.accountView?.setPostsToRecyclerView(postsUser)
    }
}