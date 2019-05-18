package com.homeaway.seattlesearch.activity.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.activity.common.ArrayAdapter
import com.homeaway.seattlesearch.activity.detail.items.DetailItemDescription
import com.homeaway.seattlesearch.activity.detail.items.DetailItemKeyValue
import com.homeaway.seattlesearch.activity.detail.items.DetailItemWeblink
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import com.homeaway.viewmodel.venue.detail.item.Type

class DetailsAdapter(inflater: LayoutInflater) : ArrayAdapter<DetailItemModel>(inflater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (Type.from(viewType)) {
            Type.KEY_VALUE -> DetailItemKeyValue(View(parent.context))
            Type.WEB_LINK -> DetailItemWeblink(View(parent.context))
            Type.DESCRIPTION -> DetailItemDescription(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.type) {
            Type.KEY_VALUE -> (holder as DetailItemKeyValue).onBind(item)
            Type.WEB_LINK -> (holder as DetailItemWeblink).onBind(item)
            Type.DESCRIPTION -> (holder as DetailItemDescription).onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }
}