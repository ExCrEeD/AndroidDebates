package com.example.debates.Actividades.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.debates.*
import com.example.debates.DataTransferObject.DTODebate
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Console
import com.google.gson.reflect.TypeToken



class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        getAllDebates()
        return root
    }

    fun getAllDebates(){
        val solicitudHttp = httpRequestDebates.create(ApiService::class.java).
             getDebates(PoliDebates.localStorage.getId())

        httpResponseArrayDebates(solicitudHttp,object: resultsCallbacks {
            override fun solicitudExitosa(response: String) {
                var debates = Gson().fromJson(response,Array<DTODebate>::class.java )
                println(response)
            }
            override fun solicitudFallida(response: String) {
                Log.v("polidebates",response)
            }
        })
    }

}