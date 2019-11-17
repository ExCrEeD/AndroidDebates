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
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.Intent
import android.provider.MediaStore
import android.graphics.Bitmap
import android.app.Activity
import android.R.attr.data
import android.R.attr.data
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Base64
import kotlinx.android.synthetic.main.nav_header_menu.*
import java.io.ByteArrayOutputStream
import java.io.IOException


class CrearDebateFragment : Fragment() {


    lateinit var imagedebate:ByteArray
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_creardebate, container, false)
        val btnCrearDebate: Button = root.findViewById(R.id.button_create_debate)
        val btnImage: Button = root.findViewById(R.id.button_pick_image)
        btnImage.setOnClickListener(View.OnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, 0)
        })

        btnCrearDebate.setOnClickListener(View.OnClickListener {
            var nuevoDebate: DTODebate = DTODebate()
            nuevoDebate.Titulo = editText_tittle_debate.text.toString()
            nuevoDebate.Tema = editText_description_debate.text.toString()
            nuevoDebate.ImageByteArray = Base64.encodeToString(imagedebate, Base64.DEFAULT)
            nuevoDebate.extensionImage = "png"
            if(!inconsistenciasFormulario(nuevoDebate)){
                createDebate(nuevoDebate)
            }
        })

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK)
        {
            val selectedPhotoUri = data?.data
            try {
                selectedPhotoUri?.let {
                val source = ImageDecoder.createSource(getActivity()!!.getContentResolver(), selectedPhotoUri)
                val bitmap = ImageDecoder.decodeBitmap(source)
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
                    imagedebate = stream.toByteArray()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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