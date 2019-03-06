package com.example.myapplication.ui_tv.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.example.myapplication.R
import timber.log.Timber


class IconHeaderItemPresenter : RowHeaderPresenter() {
    private var mUnselectedAlpha: Float = 0.toFloat()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        mUnselectedAlpha = parent.resources.getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1)
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.icon_header_item, null)
        view.alpha = mUnselectedAlpha
        return RowHeaderPresenter.ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any?) {
        val headerItem = (item as ListRow).headerItem
        val rootView = viewHolder.view
        val iconView = rootView.findViewById<ImageView>(R.id.header_icon)
        iconView.setImageDrawable(rootView.resources.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp, null))
        val label = rootView.findViewById<TextView>(R.id.header_label)
        label.text = headerItem.name
        Timber.i("label name=${headerItem.name}")
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
    }

    override fun onSelectLevelChanged(holder: RowHeaderPresenter.ViewHolder) {
        holder.view.alpha = mUnselectedAlpha + holder.selectLevel * (1.0f - mUnselectedAlpha)
    }

}
