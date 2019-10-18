package com.example.epicture.DiscoverList


import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.epicture.Model.Gallery
import com.example.epicture.R
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverListActivity : Fragment(), DiscoverListContract.View {
    private val TAG = "DiscoverListActivity"
    private var discoverListPresenter = DiscoverListPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initUI()
        discoverListPresenter.getMoreData(1)
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    /**
     * This method will initialize the UI components
     */
    private fun initUI() {
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setDataToRecyclerView(discoverArrayList: String) {
        //if (discoverArrayList.size > 0) {
        //    textView4.text = discoverArrayList[0].account_url
        //}
        textView4.text = discoverArrayList
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