package com.example.debates.Actividades

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_debate_list_item.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.debates.DataTransferObject.DTODebate
import com.example.debates.R
import com.example.debates.UrlAPI
import com.example.debates.UrlBase
import kotlin.collections.ArrayList


class DebateRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{


    private var items: List<DTODebate> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DebateViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_debate_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is DebateViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(DebateList: List<DTODebate>){
        items = DebateList
    }

    class DebateViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val debate_image = itemView.debate_image
        val debate_title = itemView.debate_title
        val debate_author = itemView.debate_author
        val debate_topic = itemView.debate_topic
        val debate_date = itemView.debate_publication_date
        val debate_votes = itemView.debate_votes



        fun bind(debate: DTODebate){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(UrlBase+"Content/themes/base/images/a.jpg")
                .into(debate_image)
            debate_title.setText(debate.Titulo)
            debate_author.setText(debate.AutorName)
            debate_topic.setText(debate.Tema)
            debate_date.setText(debate.FechaPublicacion)
            debate_votes.setText(debate.RatingCount.toString())
        }

    }

}