package com.example.epicture.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    val textabout = MutableLiveData<String>()
    val textjoined = MutableLiveData<String>()
    val textnotoriety = MutableLiveData<String>()
    fun myAbout(msg: String) {
        textabout.value = msg
    }
    fun myJoined(msg: String) {
        textjoined.value = msg
    }
    fun myNotoriety(msg: String) {
        textnotoriety.value = msg
    }
//    val text: LiveData<String> = _text
}