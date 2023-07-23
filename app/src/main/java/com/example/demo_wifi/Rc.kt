package com.example.demo_wifi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.List

class Rc(private val mList: List<WifiList>) : RecyclerView.Adapter<Rc.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_list1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.imageView.setImageResource(ItemsViewModel.images)
        holder.textView.text = ItemsViewModel.name
        Log.e("Progress", "onBindViewHolder:${ItemsViewModel.id} ", )
        holder.progressBar.progress=ItemsViewModel.id.toInt()


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.titlerc2)
        val imageView: ImageView =itemView.findViewById(R.id.icon1)
        val progressBar:ProgressBar=itemView.findViewById(R.id.progressBar)
    }
}

