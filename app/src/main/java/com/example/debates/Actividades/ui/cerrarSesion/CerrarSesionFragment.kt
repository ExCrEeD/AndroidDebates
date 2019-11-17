package com.example.debates.Actividades.ui.cerrarSesion

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.debates.R
import android.content.DialogInterface
import android.content.Intent
import android.widget.Toast
import com.example.debates.Actividades.Login
import com.example.debates.Actividades.Menu
import com.example.debates.PoliDebates


class CerrarSesionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_cerrarsesion, container, false)
        AlertDialog
            .Builder(getActivity())
            .setMessage("Confirme para cerrar sesion")
            .setPositiveButton("Simon") { dialog, which ->
                PoliDebates.localStorage.clearStoredUserInfo()
                val intent = Intent(getActivity(), Login::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Nelson") { dialog, which ->
                Toast.makeText(getActivity(),
                android.R.string.no, Toast.LENGTH_SHORT).show()
             }
            .create()
            .show()
        return root
    }
}