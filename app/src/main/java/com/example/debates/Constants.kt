package com.example.debates

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val UrlBase = "http://192.168.0.103:80/api/"
val httpRequestDebates : Retrofit = Retrofit.Builder().
    baseUrl(UrlBase).
    addConverterFactory(GsonConverterFactory.create()).
    build()
