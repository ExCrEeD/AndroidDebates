package com.example.debates.Actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.debates.ApiService
import com.example.debates.DataTransferObject.DTOUser
import com.example.debates.R
import com.example.debates.resultsCallbacks
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.debates.UrlUser as urlUser

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View)
    {
        var user: DTOUser =
            DTOUser()
        val txt_email : TextView = findViewById(R.id.txt_email) as TextView
        val txt_password : TextView = findViewById(R.id.txt_password) as TextView
        user.Email = txt_email.text.toString()
        user.Password = txt_password.text.toString()
        validateLogin(user,object: resultsCallbacks {
            override fun Succes(response: String) {
                Log.v(":D",response)
            }
            override fun Failure(response: String) {
                Log.e(":'C",response)
            }
        })
    }


    fun validateLogin(user: DTOUser, callback: resultsCallbacks)
    {
        val retrofit : Retrofit = Retrofit.Builder().
            baseUrl(urlUser).
            addConverterFactory(GsonConverterFactory.create()).
            build()

        val service = retrofit.create(ApiService::class.java)

        service.getPeople(user).enqueue(object : Callback<DTOUser> {
            override fun onResponse(call: Call<DTOUser>, response: Response<DTOUser>) {
                if (response.isSuccessful() &&  response.body() != null) {
                    callback.Succes(response.body().toString())
                }
            }
            override fun onFailure(call: Call<DTOUser>, t: Throwable) {
                callback.Failure(t.toString())
            }
        })
    }
}
