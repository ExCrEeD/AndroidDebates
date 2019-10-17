package com.example.debates

import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  ApiService{
    @GET("user/getLogin")
    fun getLoginCredentials(@Query("Email") Email:String,@Query("Password") Password:String): Call<JsonObject>

    @GET("user/getRolMenu")
    fun getRolMenu(@Query("rol") rol:String): Call<JsonObject>

    @GET("debates/")
    fun getDebates(@Query("idUsuario") idUsuario:Int): Call<JsonArray>


}

interface resultsCallbacks {
    fun solicitudExitosa(response:String)
    fun solicitudFallida(response:String)
}

 fun httpResponseDebates(httpRequestDebates: Call<JsonObject>,callback: resultsCallbacks) {
    httpRequestDebates.enqueue(object : Callback<JsonObject> {
        override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
            if (response.isSuccessful() && response.body() != null) {
                callback.solicitudExitosa(response.body().toString())
            }
        }
        override fun onFailure(call: Call<JsonObject>, t: Throwable) {
            callback.solicitudFallida(t.message.toString())
        }
    })
}

fun httpResponseArrayDebates(httpRequestDebates: Call<JsonArray>,callback: resultsCallbacks) {
    httpRequestDebates.enqueue(object : Callback<JsonArray> {
        override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
            if (response.isSuccessful() && response.body() != null) {
                callback.solicitudExitosa(response.body().toString())
            }
        }
        override fun onFailure(call: Call<JsonArray>, t: Throwable) {
            callback.solicitudFallida(t.message.toString())
        }
    })
}

