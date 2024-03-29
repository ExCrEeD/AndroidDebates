package com.example.debates

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.example.debates.Actividades.Menu
import com.example.debates.DataTransferObject.DTOMenu
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson


class PoliDebates : Application() {
    companion object {
        lateinit var localStorage: LocalStorage
    }

    override fun onCreate() {
        super.onCreate()
        localStorage = LocalStorage(applicationContext)
    }
}


class LocalStorage (context: Context) {
    val PREFS_NAME = "com.debates.Storage"
    val SHARED_NAME = "PoliDebatesStorage"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun getId() : Int {
        return prefs.getInt("id",0)
    }
    fun setId(id:Int){
        val editor = prefs.edit()
        editor.putInt("id",id)
        editor.apply()
    }

    fun getName() : String? {
        return prefs.getString("name","")
    }
    fun setName(newName:String){
        val editor = prefs.edit()
        editor.putString("name",newName)
        editor.apply()
    }

    fun getSecondName() : String? {
        return prefs.getString("secondName","")
    }
    fun setSecondName(secondName:String){
        val editor = prefs.edit()
        editor.putString("secondName",secondName)
        editor.apply()
    }

    fun getEmail() : String? {
        return prefs.getString("email","")
    }
    fun setEmail(email:String){
        val editor = prefs.edit()
        editor.putString("email",email)
        editor.apply()
    }

    fun getPassword() : String? {
        return prefs.getString("password","")
    }
    fun setPassword(password:String){
        val editor = prefs.edit()
        editor.putString("password",password)
        editor.apply()
    }

    fun getRol() : String? {
        return prefs.getString("rol","")
    }
    fun setRol(rol:String){
        val editor = prefs.edit()
        editor.putString("rol",rol)
        editor.apply()
    }

    fun getMenuCreateDebate() : Boolean {
        return prefs.getBoolean("menuCreateDebate",false)
    }
    fun setMenuCreateDebate(menuCreateDebate:Boolean){
        val editor = prefs.edit()
        editor.putBoolean("menuCreateDebate",menuCreateDebate)
        editor.apply()
    }

    fun getMenuReport() : Boolean {
        return prefs.getBoolean("menuReport",false)
    }
    fun setMenuReport(menuReport:Boolean){
        val editor = prefs.edit()
        editor.putBoolean("menuReport",menuReport)
        editor.apply()
    }

    fun getMenuScroll() : Boolean {
        return prefs.getBoolean("menuScroll",false)
    }
    fun setMenuScroll(menuScroll:Boolean){
        val editor = prefs.edit()
        editor.putBoolean("menuScroll",menuScroll)
        editor.apply()
    }

    fun getMenuUserInfo() : Boolean {
        return prefs.getBoolean("menuUserInfo",false)
    }
    fun setMenuUserInfo(menuUserInfo:Boolean){
        val editor = prefs.edit()
        editor.putBoolean("menuUserInfo",menuUserInfo)
        editor.apply()
    }

    fun getMenuRegisterUser() : Boolean {
        return prefs.getBoolean("menuRegisterUser",false)
    }
    fun setMenuRegisterUser(menuRegisterUser:Boolean){
        val editor = prefs.edit()
        editor.putBoolean("menuRegisterUser",menuRegisterUser)
        editor.apply()
    }

     fun clearStoredUserInfo(){
         PoliDebates.localStorage.setName("")
         PoliDebates.localStorage.setEmail("")
         PoliDebates.localStorage.setSecondName("")
         PoliDebates.localStorage.setRol("")
         PoliDebates.localStorage.setId(-1)
         PoliDebates.localStorage.setPassword("")
         PoliDebates.localStorage.setMenuCreateDebate(false)
         PoliDebates.localStorage.setMenuReport(false)
         PoliDebates.localStorage.setMenuScroll(false)
         PoliDebates.localStorage.setMenuUserInfo(false)
         PoliDebates.localStorage.setMenuRegisterUser(false)
     }


}