package com.example.myapplication.ui.book

import android.os.Bundle
import com.example.myapplication.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.book_details_view.*

class BookDetailsFragment : DaggerAppCompatActivity() {

    companion object {
        fun newInstance(): BookDetailsFragment {
            return BookDetailsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_details_view)
        back_press_image_button.setOnClickListener {
            super.onBackPressed()
        }
    }
}