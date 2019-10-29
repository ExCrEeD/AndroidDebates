package com.example.debates.Actividades.ui.Informes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InformesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "informes"
    }
    val text: LiveData<String> = _text
}