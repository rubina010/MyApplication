package com.example.myapplication.ui.book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.entity.BookModel
import com.example.myapplication.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class BookViewModel @Inject constructor(val app: Application, val bookRepository: BookRepository, val compositeDisposable: CompositeDisposable
                                        , val schedulerProvider: SchedulerProvider)
    : AndroidViewModel(app) {
    fun insertBookData(bookTitle: String, bookAuthor: String, bookDescription: String) {
        doAsync {
            bookRepository.insertBookData(BookModel(bookTitle, bookAuthor, bookDescription, ""))
        }
    }

    fun getAllBook() = bookRepository.getAllBookList()
}