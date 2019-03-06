package com.example.myapplication.ui.home

import android.os.Bundle
import com.example.myapplication.R
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class DashboardMainActivity : DaggerAppCompatActivity() {

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