package com.example.myapplication.ui.book

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.entity.BookModel
import timber.log.Timber
import javax.inject.Inject

class BookRepository @Inject constructor(val database: AppDatabase) {
    fun insertBookData(bookModel: BookModel) {
        Timber.i("insert created")
        return database.bookDao().insertData(bookModel)
    }

    fun getAllBookList(): LiveData<List<BookModel>> {
        return database.bookDao().getAllBook()
    }

}