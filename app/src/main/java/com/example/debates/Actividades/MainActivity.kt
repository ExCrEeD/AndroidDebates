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
import com.example.debates.DataTransferObject.DTOMenu
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson
import android.content.Intent


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
                storedUserInfo(response)
                activateGif(false)
                openMenu()
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

    fun storedUserInfo(json:String)
    {
        val gson = Gson()
        var userInfo = gson?.fromJson(json, DTOUser::class.java)
        //PoliDebates.localStorage.name = userInfo.Name
        PoliDebates.localStorage.setName(userInfo.Name)
        PoliDebates.localStorage.setEmail(userInfo.Email)
        PoliDebates.localStorage.setSecondName(userInfo.SecondName)
        PoliDebates.localStorage.setRol(userInfo.Rol)
        PoliDebates.localStorage.setId(userInfo.Id)

        val solicitudHttp = httpRequestDebates.create(ApiService::class.java)
                                            .getRolMenu(userInfo.Rol)
         //cargar opciones del menu para el perfil de usuario
        httpResponseDebates(solicitudHttp,object: resultsCallbacks {
            override fun solicitudExitosa(response: String) {
                val gson = Gson()
                var menuOptions:DTOMenu = gson?.fromJson(response, DTOMenu::class.java)
                PoliDebates.localStorage.setMenuCreateDebate(menuOptions.CreateDebate)
                PoliDebates.localStorage.setMenuReport(menuOptions.Report)
                PoliDebates.localStorage.setMenuScroll(menuOptions.Scroll)
                PoliDebates.localStorage.setMenuUserInfo(menuOptions.UserInfo)
                PoliDebates.localStorage.setMenuRegisterUser(menuOptions.RegisterUser)
            }
            override fun solicitudFallida(response: String) {
                Log.e("errorPoliDebates",response)
            }
        })
    }

    fun openMenu(){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }

}
