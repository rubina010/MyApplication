package com.example.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.learning.LearningActivity
import kotlinx.android.synthetic.main.login_view.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        login_view_login_button.setOnClickListener {
            val intent = Intent(this, LearningActivity::class.java)
            startActivity(intent)
        }
    }
}