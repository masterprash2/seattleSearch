package com.homeaway.seattlesearch.activity.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel

class SearchResultsAdapter(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<VenueItemViewHolder>() {

    private var resultsList: List<VenueListItemModel> = ArrayList()

    fun updateWithNewList(newList: List<VenueListItemModel>) {
        notifyItemRangeRemoved(0, resultsList.size)
        resultsList = newList
        notifyItemRangeInserted(0, newList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueItemViewHolder {
        return VenueItemViewHolder(View(parent.context))
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

    override fun onBindViewHolder(holder: VenueItemViewHolder, position: Int) {
        holder.bind(resultsList.get(position))
    }

}