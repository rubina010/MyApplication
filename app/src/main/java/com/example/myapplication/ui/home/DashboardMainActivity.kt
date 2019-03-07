package com.example.myapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.DashboardViewPager
import com.example.myapplication.ui.book.BookAdapter
import com.example.myapplication.ui.book.BookDetailsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_dashboard_main_activity.*
import timber.log.Timber

class DashboardMainActivity : DaggerAppCompatActivity(), BookAdapter.OnItemClickListener {

    private lateinit var adapter: DashboardViewPager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.songs -> {
                gotoPage(0)
                Timber.i("currentitem $0")
                return@OnNavigationItemSelectedListener true
            }
            R.id.movies -> {
                gotoPage(1)
                Timber.i("currentitem $1")
                return@OnNavigationItemSelectedListener true
            }
            R.id.books -> {
                gotoPage(2)
                Timber.i("currentitem $2")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_main_activity)
        adapter = DashboardViewPager(supportFragmentManager)
        dashboard_view_pager.offscreenPageLimit = 3
        dashboard_view_pager.adapter = adapter
        initialize()
    }

    private fun initialize() {
        Timber.i("this is called dashboardhome")
        //  showFragment(HomeFragment.newInstance(), "HomeFragment")

    }

    private fun gotoPage(pageNo: Int) {
        dashboard_view_pager.currentItem = pageNo
    }

    override fun onResume() {
        super.onResume()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onItemClick() {
        val intent = Intent(this@DashboardMainActivity, BookDetailsFragment::class.java)
        startActivity(intent)
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment, name: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, name)
                .addToBackStack(name)
                .commit()
    }

}