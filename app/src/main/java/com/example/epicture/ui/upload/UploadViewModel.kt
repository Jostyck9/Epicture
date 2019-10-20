package com.example.epicture.ui.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UploadViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Incoming Feature"
    }
    val text: LiveData<String> = _text
}