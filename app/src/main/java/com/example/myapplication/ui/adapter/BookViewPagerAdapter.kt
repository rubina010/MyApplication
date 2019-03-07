package com.example.myapplication.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.R

class BookViewPagerAdapter : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var resId = 0
        when (position) {
            0 -> resId = R.id.book_name_recycler_view
            1 -> resId = R.id.author_name_recycler_view
        }
        return container.findViewById(resId)
    }

    override fun getCount(): Int {
        return 2
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "By Book Name"
            1 -> title = "By Author Name"
        }
        return title
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}