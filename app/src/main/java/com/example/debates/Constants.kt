package com.example.debates

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val UrlBase = "http://192.168.0.103:80/"
const val UrlAPI = "http://192.168.0.103:80/api/"
val httpRequestDebates : Retrofit = Retrofit.Builder().
    baseUrl(UrlAPI).
    addConverterFactory(GsonConverterFactory.create()).
    build()
