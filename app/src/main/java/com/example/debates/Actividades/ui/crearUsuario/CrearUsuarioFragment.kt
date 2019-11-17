package com.example.debates.Actividades.ui.crearUsuario

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.debates.*
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_crearusuario.*


class CrearUsuarioFragment : Fragment() {
    var activity: Activity? = getActivity()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_crearusuario, container, false)
        val btnCrearUsuario: Button = root.findViewById(R.id.button_create_debate)
        btnCrearUsuario.setOnClickListener(View.OnClickListener {
            var nuevoUsuario: DTOUser = DTOUser()
            nuevoUsuario.Name = editText_nombre.text.toString()
            nuevoUsuario.SecondName = editText_apellido.text.toString()
            nuevoUsuario.Email = editText_email.text.toString()
            nuevoUsuario.Password = editText_clave.text.toString()
            if (spinner_rol.getSelectedItem().toString() == "Profesor") {
                nuevoUsuario.Rol = "Prelector"
            } else {
                nuevoUsuario.Rol = "Student"
            }
            if (!inconsistenciasFormulario(nuevoUsuario)) {
                crearUsuario(nuevoUsuario)
            }
        })
        val values = arrayOf("Estudiante", "Profesor")
        val spinner = root.findViewById(R.id.spinner_rol) as Spinner
        val adapter =
            ArrayAdapter(this.getActivity()!!, android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter
        return root
    }


    private fun crearUsuario(nuevoUsuario: DTOUser) {
        val solicitudHttp = httpRequestDebates.create(ApiService::class.java)
            .postAddUser(Gson().fromJson(Gson().toJson(nuevoUsuario), JsonObject::class.java))

        httpResponseDebates(solicitudHttp, object : resultsCallbacks {
            override fun solicitudExitosa(response: String) {
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

    private fun limpiarControles()
    {
        editText_nombre.setText("")
        editText_apellido.setText("")
        editText_email.setText("")
        editText_clave.setText("")
    }
    private fun inconsistenciasFormulario(usuario: DTOUser): Boolean {
        var errorMessage = ""

        if (usuario.Name.isNullOrEmpty()) {
            errorMessage = "debe ingresar un nombre "
        }
        if (usuario.SecondName.isNullOrEmpty()) {
            errorMessage = "debe ingresar un apellido"
        }
        if (usuario.Email.isNullOrEmpty()) {
            errorMessage = "debe ingresar un Email"
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(usuario.Email).matches()) {
            errorMessage = "debe ingresar una direccion de correo valida"
        }
        if (usuario.Password.isNullOrEmpty()) {
            errorMessage = "debe ingresar una contraseña"
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
}