package com.example.myapplication.ui.book

import com.example.myapplication.database.AppDatabase1
import com.example.myapplication.entity.BookModel
import javax.inject.Inject

class BookRepository @Inject constructor(val database: AppDatabase1) {
    fun insertBookData(bookModel: BookModel) = database.bookDao().insertData(bookModel)
}