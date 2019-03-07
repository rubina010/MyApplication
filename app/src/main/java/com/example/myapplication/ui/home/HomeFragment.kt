package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.ViewPagerAdapter
import com.example.myapplication.utils.ALL_SONGS
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : DaggerFragment() {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    private lateinit var allSongsAdapter: CustomListAdapter
    private lateinit var popularSongsAdapter: CustomListAdapter
    private lateinit var favouriteSongsAdapter: CustomListAdapter
    private lateinit var tabAllSongs: TabLayout.Tab
    private lateinit var tabPopularSongs: TabLayout.Tab
    private lateinit var tabFavouriteSongs: TabLayout.Tab
    private lateinit var tabLayout: TabLayout.Tab
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        adapter = ViewPagerAdapter()
        activity_dashboard_main_viewpager.offscreenPageLimit = 3
        activity_dashboard_main_viewpager.adapter = adapter
        tabAllSongs = activity_dashboard_tab_layout.newTab()
        tabPopularSongs = activity_dashboard_tab_layout.newTab()
        tabFavouriteSongs = activity_dashboard_tab_layout.newTab()
        activity_dashboard_tab_layout.addTab(tabAllSongs.setCustomView(R.layout.view_pager_item1))
        activity_dashboard_tab_layout.addTab(tabPopularSongs.setCustomView(R.layout.view_pager_item2))
        activity_dashboard_tab_layout.addTab(tabFavouriteSongs.setCustomView(R.layout.view_pager_item3))
        activity_dashboard_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabLayout = tab!!
                activity_dashboard_main_viewpager.currentItem = tab.position
                tabSelected()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tabLayout = tab!!
                tabUnSelected()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        val items = resources.getStringArray(R.array.all_songs_list)
        //  activity_dashboard_tab_layout.setupWithViewPager(activity_dashboard_main_viewpager)
        allSongsAdapter = CustomListAdapter(context!!, items)
        all_song_list_recycler_view.layoutManager = LinearLayoutManager(context!!)
        all_song_list_recycler_view.adapter = allSongsAdapter

        popularSongsAdapter = CustomListAdapter(context!!, items)
        popular_song_list_recycler_view.layoutManager = LinearLayoutManager(context!!)
        popular_song_list_recycler_view.adapter = popularSongsAdapter

        favouriteSongsAdapter = CustomListAdapter(context!!, items)
        favourite_song_list_recycler_view.layoutManager = LinearLayoutManager(context!!)
        favourite_song_list_recycler_view.adapter = favouriteSongsAdapter

    }

    private fun tabSelected() {
        changeColor(R.id.view_pager_item1_text_view, R.color.black)
        changeColor(R.id.view_pager_item2_text_view, R.color.black)
        changeColor(R.id.view_pager_item3_text_view, R.color.black)
    }

    private fun tabUnSelected() {
        changeColor(R.id.view_pager_item1_text_view, R.color.lb_grey)
        changeColor(R.id.view_pager_item2_text_view, R.color.lb_grey)
        changeColor(R.id.view_pager_item3_text_view, R.color.lb_grey)
    }

    private fun changeColor(id: Int, color: Int) {
        tabLayout.customView?.findViewById<TextView>(id)?.setTextColor(ContextCompat.getColor(activity!!, color))
    }
}