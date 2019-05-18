package com.homeaway.seattlesearch.activity.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.activity.common.ArrayAdapter
import com.homeaway.seattlesearch.databinding.ItemVenueBinding
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel

class SearchResultsAdapter(
    private val inflater: LayoutInflater
) : ArrayAdapter<VenueListItemModel>(inflater) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueItemViewHolder {
        return VenueItemViewHolder(ItemVenueBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VenueItemViewHolder).bind(getItem(position))
    }


}