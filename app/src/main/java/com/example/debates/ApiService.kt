package com.example.debates

import android.provider.SyncStateContract
import com.google.gson.JsonObject
import org.json.JSONArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface  ApiService{
    @GET("getlogin/")
    fun getPeople(@Query("logincredentials") logincredentials:DTOUser): Call<DTOUser>

}
interface resultsCallbacks {
    fun Succes(response:String)
    fun Failure(response:String)
}

