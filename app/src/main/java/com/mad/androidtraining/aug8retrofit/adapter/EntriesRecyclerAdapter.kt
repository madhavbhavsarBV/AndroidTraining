package com.mad.androidtraining.aug8retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mad.androidtraining.R
import com.mad.androidtraining.aug8retrofit.models.EntriesList

class EntriesRecyclerAdapter(val context:Context, val data : List<EntriesList>) : RecyclerView.Adapter<EntriesRecyclerAdapter.EntriesViewHolder>() {

    class EntriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvApi = itemView.findViewById<TextView>(R.id.tvApi)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvAuth = itemView.findViewById<TextView>(R.id.tvAuth)
        val tvHttps = itemView.findViewById<TextView>(R.id.tvHttps)
        val tvCors = itemView.findViewById<TextView>(R.id.tvCors)
        val tvLink = itemView.findViewById<TextView>(R.id.tvLink)
        val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_entries,parent,false)
        return EntriesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: EntriesViewHolder, position: Int) {
        val model = data[position]

        holder.tvApi.text = "API : "+model.api.toString()
        holder.tvDescription.text = "Desc : "+ model.description.toString()
        holder.tvCors.text = "Cors : "+ model.cors.toString()
        holder.tvHttps.text = "Https : "+ model.https.toString()
        holder.tvLink.text ="Link : "+  model.link.toString()
        holder.tvCategory.text ="Cat : "+  model.category.toString()
        holder.tvAuth.text = "Auth : "+ model.auth.toString()
    }
}