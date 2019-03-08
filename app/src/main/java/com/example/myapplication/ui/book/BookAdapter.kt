package com.example.myapplication.ui.book

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entity.BookModel
import javax.xml.transform.Result

private val RESPONSE_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.systemId == newItem.systemId
    }

}

class BookAdapter(val itemList: MutableList<BookModel>, var listener: BookAdapter.OnItemClickListener?) : RecyclerView.Adapter<BookAdapter.BookAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: com.example.myapplication.databinding.BookRecyclerViewItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.book_recycler_view_item_list, parent, false)
        return BookAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookAdapterViewHolder, position: Int) {
        val model = itemList[position]
        holder.viewHolderBinding.bookInfo = model
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        listener = null
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class BookAdapterViewHolder(binding: com.example.myapplication.databinding.BookRecyclerViewItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        val viewHolderBinding: com.example.myapplication.databinding.BookRecyclerViewItemListBinding = binding
    }

    interface OnItemClickListener {
        fun onItemClick()
    }

    class MyDiffUtilCallback(val oldBookData: MutableList<BookModel>, val newBookData: MutableList<BookModel>) : DiffUtil.Callback() {
        override fun getNewListSize(): Int {
            return newBookData.size
        }

        override fun getOldListSize(): Int {
            return oldBookData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItemPosition == newItemPosition
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldBookData[oldItemPosition] == newBookData[newItemPosition]
        }
    }
}