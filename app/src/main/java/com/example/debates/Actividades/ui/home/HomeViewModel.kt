package com.example.debates.Actividades.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui van los pinshes debates"
    }
    val text: LiveData<String> = _text
}