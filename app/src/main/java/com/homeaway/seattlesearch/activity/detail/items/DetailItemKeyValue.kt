package com.homeaway.seattlesearch.activity.detail.items

import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.databinding.ItemDetailKeyValueBinding
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel

class DetailItemKeyValue(private val binding: ItemDetailKeyValueBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(itemModel: DetailItemModel) {
        binding.model = itemModel
    }
}