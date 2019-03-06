package com.example.myapplication.ui.learning

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.IntroViewPagerAdapter
import com.example.myapplication.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_learning_view.*

class LearningActivity : AppCompatActivity() {
    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_view)

        val viewPagerAdapter = IntroViewPagerAdapter(this)
        activity_learning_view_view_pager.adapter = viewPagerAdapter
        dotscount = viewPagerAdapter.count
        dots = arrayOfNulls(dotscount)

        activity_learning_view_previous_button.setOnClickListener {
            activity_learning_view_view_pager.setCurrentItem(getItem(+1), true)
        }
        activity_learning_view_next_button.setOnClickListener {
            activity_learning_view_view_pager.setCurrentItem(getItem(-1), true)
        }
        activity_learning_view_finish_button.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }
        for (i in 0 until dotscount) {
            dots!![i] = ImageView(this)
            dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_bar_selected_inactive_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(9, 0, 9, 0)
            activity_learning_view_tab_slider_dots.addView(dots!![i], params)
        }
        dots!![0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_bar_selected_active_dot))
        activity_learning_view_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_bar_selected_inactive_dot))
                }
                dots!![position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_bar_selected_active_dot))
                when (position) {
                    3 -> {
                        activity_learning_view_next_button.visibility = View.GONE
                        activity_learning_view_finish_button.visibility = View.VISIBLE
                    }
                    else -> {
                        activity_learning_view_next_button.visibility = View.VISIBLE
                        activity_learning_view_finish_button.visibility = View.GONE
                    }
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun getItem(i: Int): Int {
        return activity_learning_view_view_pager.currentItem + i
    }
}