package com.example.myapplication.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.entity.BookModel
import com.example.myapplication.ui.adapter.BookViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_book.*
import javax.inject.Inject

class BookFragment : DaggerFragment() {
    @Inject
    lateinit var bookViewModel: BookViewModel

    private var bookTitle: String = ""
    private var bookAuthor: String = ""
    private var bookDescription: String = ""
    val itemBooks = mutableListOf<BookModel>()

    private lateinit var bookAdapter: BookAdapter
    private lateinit var authorAdapter: BookAdapter
    private lateinit var tabLayout: TabLayout.Tab
    private lateinit var tabBookName: TabLayout.Tab
    private lateinit var tabAuthorName: TabLayout.Tab
    private lateinit var adapter: BookViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        adapter = BookViewPagerAdapter()
        fragment_book_view_pager.offscreenPageLimit = 2
        fragment_book_view_pager.adapter = adapter
        tabBookName = fragment_book_tab_layout.newTab()
        tabAuthorName = fragment_book_tab_layout.newTab()
        fragment_book_tab_layout.setupWithViewPager(fragment_book_view_pager)
        fragment_book_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabLayout = tab!!
                fragment_book_view_pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tabLayout = tab!!
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        initData()
        bookAdapter = BookAdapter(itemBooks, activity as BookAdapter.OnItemClickListener)
        book_name_recycler_view.layoutManager = LinearLayoutManager(context!!)
        book_name_recycler_view.adapter = bookAdapter

        authorAdapter = BookAdapter(itemBooks, activity as BookAdapter.OnItemClickListener)
        author_name_recycler_view.layoutManager = LinearLayoutManager(context!!)
        author_name_recycler_view.adapter = authorAdapter

        fragment_book_add_text_view.setOnClickListener {
            fragment_book_add_linear_layout.visibility = View.VISIBLE
        }
        fragment_book_add_button.setOnClickListener {
            if (fragment_book_title_edit_text.text.isEmpty() || fragment_book_author_name_edit_text.text.isEmpty() ||
                    fragment_book_description_edit_text.text.isEmpty()) {
                Toast.makeText(context, "Some required field is empty", Toast.LENGTH_LONG).show()
            } else {
                bookTitle = fragment_book_title_edit_text.text.toString()
                bookAuthor = fragment_book_author_name_edit_text.text.toString()
                bookDescription = fragment_book_description_edit_text.text.toString()
                bookViewModel.insertBookData(bookTitle, bookAuthor, bookDescription)
                Toast.makeText(context, "Book Added successfully", Toast.LENGTH_LONG).show()
                val newBookList = itemBooks
                newBookList.apply {
                    val diffResult = DiffUtil.calculateDiff(BookAdapter.MyDiffUtilCallback(itemBooks, this))
                    itemBooks.clear()
                    itemBooks.addAll(this)
                    diffResult.dispatchUpdatesTo(bookAdapter)
                }
                fragment_book_add_linear_layout.visibility = View.GONE
            }

        }
    }

    fun initData() {
        bookViewModel.getAllBook().observe(this, Observer {
            if (it != null) {
                itemBooks.addAll(it)
            }
        })
    }

    private fun showAlertDialog() {

    }
}