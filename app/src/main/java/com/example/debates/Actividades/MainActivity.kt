package com.example.debates.Actividades


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.debates.*
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.debates.DataTransferObject.DTOMenu
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activateGif(true)
        val solicitudHttp = httpRequestDebates.create(ApiService::class.java)
            .getLoginCredentials(PoliDebates.localStorage.getEmail().toString(),PoliDebates.localStorage.getPassword().toString())

        Log.v("Polidebates","log1")
        httpResponseDebates(solicitudHttp,object: resultsCallbacks {
            override fun solicitudFallida(response: String) {
                activateGif(false)
                openLogin()
            }

            override fun solicitudExitosa(response: String) {
              activateGif(false)
              storedUserInfo(response)
            }
        })
    }
    fun openMenu(){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
    fun openLogin(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun activateGif(activar:Boolean)
    {
        val imageView : ImageView = findViewById(R.id.ImageView_charge) as ImageView
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

    private fun storedUserInfo(json:String)
    {
        val gson = Gson()
        var user = gson.fromJson(json, DTOUser::class.java)
        PoliDebates.localStorage.setName(user.Name)
        PoliDebates.localStorage.setEmail(user.Email)
        PoliDebates.localStorage.setSecondName(user.SecondName)
        PoliDebates.localStorage.setRol(user.Rol)
        PoliDebates.localStorage.setId(user.Id)
        PoliDebates.localStorage.setPassword(user.Password)
        val solicitudHttp = httpRequestDebates.create(ApiService::class.java)
            .getRolMenu(user.Rol)
        //cargar opciones del menu para el perfil de usuario
        httpResponseDebates(solicitudHttp,object: resultsCallbacks {
            override fun solicitudExitosa(response: String) {
                var menuOptions: DTOMenu = Gson().fromJson(response, DTOMenu::class.java)
                PoliDebates.localStorage.setMenuCreateDebate(menuOptions.CreateDebate)
                PoliDebates.localStorage.setMenuReport(menuOptions.Report)
                PoliDebates.localStorage.setMenuScroll(menuOptions.Scroll)
                PoliDebates.localStorage.setMenuUserInfo(menuOptions.UserInfo)
                PoliDebates.localStorage.setMenuRegisterUser(menuOptions.RegisterUser)
                openMenu()
            }
            override fun solicitudFallida(response: String) {
                Log.e("errorPoliDebates",response)
            }
        })
    }
}
