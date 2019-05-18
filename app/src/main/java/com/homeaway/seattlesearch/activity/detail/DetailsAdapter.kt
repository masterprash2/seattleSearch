package com.homeaway.seattlesearch.activity.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.activity.common.ArrayAdapter
import com.homeaway.seattlesearch.activity.detail.items.DetailItemKeyValue
import com.homeaway.seattlesearch.databinding.ItemDetailKeyValueBinding
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel

class DetailsAdapter(private val inflater: LayoutInflater) : ArrayAdapter<DetailItemModel>(inflater) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DetailItemKeyValue(ItemDetailKeyValueBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailItemKeyValue).onBind(getItem(position))
    }

}