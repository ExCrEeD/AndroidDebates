package com.example.debates

import com.example.debates.DataTransferObject.DTOUser
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

