package com.example.debates.Actividades.ui.crearUsuario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CrearUsuarioViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "crear usuario"
    }
    val text: LiveData<String> = _text
}