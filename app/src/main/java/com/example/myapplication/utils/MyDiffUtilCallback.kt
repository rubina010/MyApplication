package com.example.myapplication.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.entity.BookModel

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