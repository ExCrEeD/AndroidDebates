package com.example.debates.Actividades.ui.crearDebate

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_crearusuario.*
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import com.example.debates.*
import com.example.debates.DataTransferObject.DTODebate
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_creardebate.*

class CrearDebateFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_creardebate, container, false)
        val btnCrearDebate: Button = root.findViewById(R.id.button_create_debate)
        btnCrearDebate.setOnClickListener(View.OnClickListener {
            var nuevoDebate: DTODebate = DTODebate()
            nuevoDebate.Titulo = editText_tittle_debate.text.toString()
            nuevoDebate.Tema = editText_description_debate.text.toString()
            if(!inconsistenciasFormulario(nuevoDebate)){
                createDebate(nuevoDebate)
            }
        })
        return root
    }

    fun createDebate(nuevoDebate: DTODebate){
        nuevoDebate.Autor =  PoliDebates.localStorage.getId();
        val solicitudHttp = httpRequestDebates.create(ApiService::class.java)
            .postAddDebate(Gson().fromJson(Gson().toJson(nuevoDebate), JsonObject::class.java))

        httpResponseDebates(solicitudHttp, object : resultsCallbacks {
            override fun solicitudExitosa(response: String) {
                Log.e("errorPoliDebates", response)
            }

            override fun solicitudFallida(response: String) {
                Log.e("errorPoliDebates", response)
            }
        })
        AlertDialog
            .Builder(getActivity())
            .setMessage("usuario Registrado Correctamente")
            .create()
            .show()
        limpiarControles()
    }

    private fun inconsistenciasFormulario(nuevoDebate: DTODebate): Boolean {
        var errorMessage = ""

        if (nuevoDebate.Titulo.isNullOrEmpty()) {
            errorMessage = "debe ingresar un titulo del debate "
        }
        if (nuevoDebate.Tema.isNullOrEmpty()) {
            errorMessage = "debe ingresar una descripcion del debate"
        }

        if (errorMessage.isNotEmpty()) {
            Toast.makeText(
                getActivity(),
                errorMessage,
                Toast.LENGTH_LONG
            ).show()
        }
        return errorMessage.isNotEmpty()
    }

    private fun limpiarControles(){
        editText_description_debate.setText("")
        editText_tittle_debate.setText("")
    }
}