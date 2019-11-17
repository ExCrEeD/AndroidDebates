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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.debates.*
import com.example.debates.Actividades.DebateRecyclerAdapter
import com.example.debates.Actividades.ui.TopSpacingItemDecoration
import com.example.debates.DataTransferObject.DTODebate
import com.example.debates.DataTransferObject.DTOUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Console
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.R.attr.data




class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        addDataSet(root)
        return root
    }

    private fun addDataSet(root:View){
        val solicitudHttp = httpRequestDebates.create(ApiService::class.java).
            getDebates(PoliDebates.localStorage.getId())

        httpResponseArrayDebates(solicitudHttp,object: resultsCallbacks {
            override fun solicitudExitosa(response: String) {
                lateinit var debateAdapter: DebateRecyclerAdapter
                debateAdapter = DebateRecyclerAdapter()
                val gson = Gson()
                val itemType = object : TypeToken<List<DTODebate>>() {}.type
                val debates = gson.fromJson<List<DTODebate>>(response, itemType)
                root.recycler_view.apply {
                    layoutManager = LinearLayoutManager(getActivity())
                    val topSpacingDecorator = TopSpacingItemDecoration(30)
                    addItemDecoration(topSpacingDecorator)
                    adapter = debateAdapter
                }
                debateAdapter.submitList(debates)
                println(response)
            }
            override fun solicitudFallida(response: String) {
                Log.v("polidebates",response)
            }
        })
    }



}