package com.mad.androidtraining.july31retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mad.androidtraining.R
import com.mad.androidtraining.july31retrofit.models.UsersData

class UserRecyclerViewAdapter(val context:Context, val data : List<UsersData>) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvID = itemView.findViewById<TextView>(R.id.tvID)
        val tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
        val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
        val tvLastName = itemView.findViewById<TextView>(R.id.tvLastName)
        val sivAvatar = itemView.findViewById<ImageView>(R.id.sivAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_usersrv, parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val model = data[position]

        holder.tvID.text = model.id.toString()
        holder.tvEmail.text = model.email
        holder.tvFirstName.text = model.first_name
        holder.tvLastName.text = model.last_name

        Glide
            .with(context)
            .load(model.avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_loading_spinner)
            .into(holder.sivAvatar);
    }
}