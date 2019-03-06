package com.example.myapplication.ui.home

import android.os.Bundle
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class DashboardMainActivity : DaggerAppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.songs -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.movies -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.books -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_main_activity)
        initialize()
    }

    private fun initialize() {
        Timber.i("this is called dashboardhome")
        showFragment(HomeFragment.newInstance(), "HomeFragment")
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment, name: String) {
        Timber.i("this is called dashboardhome")
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, name)
                .addToBackStack(name)
                .commit()
    }
}