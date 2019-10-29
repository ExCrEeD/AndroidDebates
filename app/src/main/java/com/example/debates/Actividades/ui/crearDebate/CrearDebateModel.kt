package com.example.debates.Actividades.ui.crearDebate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CrearDebateModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Crear nuevo debate"
    }
    val text: LiveData<String> = _text
}