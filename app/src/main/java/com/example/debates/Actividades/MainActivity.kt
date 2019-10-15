package com.example.debates.Actividades

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.debates.*
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View)
    {
        activateGif(true)
        var user: DTOUser = DTOUser()
        val txt_email : TextView = findViewById(R.id.txt_email) as TextView
        val txt_password : TextView = findViewById(R.id.txt_password) as TextView
        user.Email = txt_email.text.toString()
        user.Password = txt_password.text.toString()

        val solicitudHttp = httpRequestDebates.create(ApiService::class.java)
                                            .getLoginCredentials(user.Email,user.Password)
        httpResponseDebates(solicitudHttp,object: resultsCallbacks {
            override fun solicitudExitosa(response: String) {
//                Log.v("qwerts",response)
                var gson = Gson()
                var userInfo = gson?.fromJson(response, DTOUser::class.java)
                Log.v("qwerts",userInfo.Name)
                Log.v("qwerts",userInfo.SecondName)
               // activateGif(false)

            }
            override fun solicitudFallida(response: String) {
                activateGif(false)
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Inicio de sesion")
                builder.setMessage("usuario o contraseña incorrectos")
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        })
    }

    fun activateGif(activar:Boolean)
    {
        val imageView : ImageView = findViewById(R.id.ImageView_cargando) as ImageView
        if(activar)
        {
            var id = getResources().getIdentifier("@drawable/cargando", null, getPackageName())
            imageView.setVisibility(View.VISIBLE)
            Glide.with(getApplicationContext()).load(id).into(imageView);
            imageView.setBackgroundColor(Color.TRANSPARENT)
        }
        else
        {
            imageView.setVisibility(View.INVISIBLE)
        }
    }



}
