package com.example.debates.Actividades.ui.cerrarSesion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CerrarSesionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "cerrar sesion"
    }
    val text: LiveData<String> = _text
}