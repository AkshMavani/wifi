package com.example.demo_wifi


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.List


class CustomAdapter(private val mList: List<WifiList>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

       holder.imageView.setImageResource(ItemsViewModel.images)
        holder.textView.text = ItemsViewModel.name
        holder.button.setOnClickListener {
                val alertDialog = AlertDialog.Builder(it.context,R.style.MyDialogTheme)
                alertDialog.setTitle("Wifi Information")
              alertDialog.setIcon(R.drawable.wifia)
            alertDialog.setMessage("Wifi ID : "+ItemsViewModel.id+"\n"+"Wifi Name:  "+ItemsViewModel.name)
                alertDialog.setPositiveButton("OK"
                ) { dialog, which -> dialog.cancel() }
                val dialog = alertDialog.create()
                dialog.show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.titlerc)
        val imageView:ImageView=itemView.findViewById(R.id.icon)
        val button:TextView=itemView.findViewById(R.id.txtBtn)
    }

}

