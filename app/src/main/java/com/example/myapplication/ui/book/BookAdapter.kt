package com.example.myapplication.ui.book

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import org.jetbrains.anko.layoutInflater

class BookAdapter(val itemList: Array<String>, var listener: BookAdapter.OnItemClickListener?) : RecyclerView.Adapter<BookAdapter.BookAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapterViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.book_recycler_view_item_list, parent, false)
        return BookAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookAdapterViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.textView.setOnClickListener {
            listener!!.onItemClick()
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        listener = null
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class BookAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.book_name_text_view)
        fun bind(text: String) {
            textView.text = text
        }
    }

    interface OnItemClickListener {
        fun onItemClick()
    }
}