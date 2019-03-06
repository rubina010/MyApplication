package com.example.myapplication.ui_tv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.R
import com.example.myapplication.TvAppFragment
import com.example.myapplication.ui_tv.data.DataListViewModel
import javax.inject.Inject

class TvAppActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Leanback)
        setContentView(R.layout.activity_main)
        showFragment()
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, TvAppFragment.newInstance(), "TvAppFragment")
                .addToBackStack("TvAppFragment")
                .commit()
    }
}