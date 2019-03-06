package com.example.myapplication.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R

class ViewPagerAdapter : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var resID = 0
        when (position) {
            0 -> resID = R.id.all_song_list_recycler_view
            1 -> resID = R.id.popular_song_list_recycler_view
            2 -> resID = R.id.favourite_song_list_recycler_view
        }
        return container.findViewById(resID)
    }

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "All Songs"
            1 -> title = "Popular Songs"
            2 -> title = "Favourite Songs"
        }
        return title
    }
}