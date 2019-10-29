package com.example.debates.Actividades.ui.crearDebate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.debates.R

class CrearDebateFragment : Fragment() {

    private lateinit var crearDebateModel: CrearDebateModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        crearDebateModel =
            ViewModelProviders.of(this).get(CrearDebateModel::class.java)
        val root = inflater.inflate(R.layout.fragment_creardebate, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        crearDebateModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}