package com.example.epicture.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.epicture.R

class AccountFragment : Fragment() {
    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel =
            ViewModelProviders.of(this).get(AccountViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_account, container, false)

        val textView: TextView = root.findViewById(R.id.info_about)
        accountViewModel.myAbout("Wesh alors")
        accountViewModel.textabout.observe(this, Observer {
            textView.text = it
        })

        val textJoined: TextView = root.findViewById(R.id.info_joined)
        accountViewModel.myJoined("Wesh pas alors")
        accountViewModel.textjoined.observe(this, Observer {
            textJoined.text = it
        })

        val textNotoriety: TextView = root.findViewById(R.id.info_notoriety)
        accountViewModel.myNotoriety("Wesh t fragile")
        accountViewModel.textnotoriety.observe(this, Observer {
            textNotoriety.text = it
        })
        return root
    }
}