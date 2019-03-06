package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        // showFragment(TvAppFragment.newInstance())
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, "TvAppFragment")
                .commit()
    }
}
