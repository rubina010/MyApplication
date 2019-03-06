package com.example.myapplication.ui_tv.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.leanback.widget.Presenter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.ui_tv.entity.SongEntity
import kotlinx.android.synthetic.main.grid_item_view.view.*
import kotlinx.android.synthetic.main.icon_header_item.view.*


class GridItemPresenter(val context: Context, private val headerName: String) : Presenter() {
     override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.grid_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val songEntity=SongEntity::class.java
        when (headerName) {
            "Songs" -> {
                viewHolder.view.grid_item_relative_layout.setBackgroundResource(R.drawable.music)
               viewHolder.view.grid_item_name.text =songEntity.name.toString()
            }
            "Videos" -> {
                viewHolder.view.grid_item_relative_layout.setBackgroundResource(R.drawable.video)
            }
            "Movies" -> {
                viewHolder.view.grid_item_relative_layout.setBackgroundResource(R.drawable.movie)
            }
        }

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
