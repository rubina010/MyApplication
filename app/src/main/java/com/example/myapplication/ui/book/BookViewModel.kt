package com.example.myapplication.ui.book

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.entity.BookModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BookViewModel @Inject constructor(val app: Application, val bookRepository: BookRepository, val compositeDisposable: CompositeDisposable)
    : AndroidViewModel(app) {
    fun insertBookData() {
        bookRepository.insertBookData(BookModel(1, "Into The Thin Air", "Jon Krakaure", "image", "image"))
    }
}