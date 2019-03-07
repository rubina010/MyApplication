package com.example.myapplication.ui.book

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.BookViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_book.*

class BookFragment : DaggerFragment(){
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
        val items = resources.getStringArray(R.array.all_books)
        bookAdapter = BookAdapter(items, activity as BookAdapter.OnItemClickListener)
        book_name_recycler_view.layoutManager = LinearLayoutManager(context!!)
        book_name_recycler_view.adapter = bookAdapter

        val authorItems = resources.getStringArray(R.array.all_author)
        authorAdapter = BookAdapter(authorItems, activity as BookAdapter.OnItemClickListener)
        author_name_recycler_view.layoutManager = LinearLayoutManager(context!!)
        author_name_recycler_view.adapter = authorAdapter

    }
}