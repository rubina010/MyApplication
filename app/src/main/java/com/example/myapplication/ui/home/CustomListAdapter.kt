package com.example.myapplication.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CustomListAdapter(val context: Context, val itemList: Array<String>) : RecyclerView.Adapter<CustomListAdapter.CustomListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomListAdapter.CustomListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_view_list_item, parent, false)
        return CustomListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomListViewHolder, position: Int) {
        val item = itemList.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class CustomListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.songs_name_text_view)
        fun bind(text: String) {
            textView.text = text
        }
    }

}