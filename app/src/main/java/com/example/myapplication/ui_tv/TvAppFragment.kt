package com.example.myapplication

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.example.myapplication.ui_tv.data.DataListViewModel
import com.example.myapplication.ui_tv.utils.GridItemPresenter
import com.example.myapplication.ui_tv.utils.IconHeaderItemPresenter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class TvAppFragment : BrowseSupportFragment() {

    private val headerItemList = mutableListOf<HeaderItem>()
    private var categoryRowAdapter: ArrayObjectAdapter? = null
    var listOfVideos = arrayListOf("Youth", "Closer")
    var listOfMovies = arrayListOf("Taking 5", "Everest", "Barnyard")
    @Inject
    lateinit var dataListViewModel: DataListViewModel

    companion object {
        fun newInstance(): TvAppFragment {
            return TvAppFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        categoryRowAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = categoryRowAdapter
        setupUIElements()
    }

    private fun setupUIElements() {
        // setBadgeDrawable(getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner));
        title = "Hello World" // Badge, when set, takes precedent
        // over title
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // set fastLane (or headers) background color
        brandColor = ContextCompat.getColor(activity!!, android.R.color.holo_blue_bright)
        // set search icon color
        searchAffordanceColor = ContextCompat.getColor(activity!!, android.R.color.holo_orange_light)
        setHeaderPresenterSelector(object : PresenterSelector() {
            override fun getPresenter(item: Any?): Presenter {
                return IconHeaderItemPresenter()
            }

        })
        insertDataIntoDatabase()
        headerItemList.add(HeaderItem(0, "Home"))
        headerItemList.add(HeaderItem(1, "Songs"))
        headerItemList.add(HeaderItem(2, "Videos"))
        headerItemList.add(HeaderItem(3, "Movies"))
        headerItemList.forEach {
            val gridItemPresenter = GridItemPresenter(context!!, it.name)
            val gridRowAdapter = ArrayObjectAdapter(gridItemPresenter)
            when (it.name) {
                "Songs" -> {
                    gridRowAdapter.clear()
                    val listOfSongs = dataListViewModel.getSongs()
                    gridRowAdapter.add(listOfSongs)
                    /*  dataListViewModel.getSongs().observe(this, Observer {
                          if (it != null) {
                              Timber.i("songlistfromdb ${it.size}")
                              gridRowAdapter.add(it)
                          }
                      })*/
                }
                "Videos" -> {
                    gridRowAdapter.clear()
                    dataListViewModel.listOfVideos.forEach {
                        gridRowAdapter.add(it)
                    }
                }
                "Movies" -> {
                    gridRowAdapter.clear()
                    dataListViewModel.listOfMovies.forEach {
                        gridRowAdapter.add(it)
                    }
                }
            }
            val row = ListRow(it, gridRowAdapter)
            categoryRowAdapter?.add(row)
        }
    }

    fun insertDataIntoDatabase() {
        dataListViewModel.addSongsToDb("Paris", "The chainsmoker")
        dataListViewModel.getSongs()

    }

}
