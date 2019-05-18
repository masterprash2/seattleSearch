package com.homeaway.seattlesearch.activity.common

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel

abstract class ArrayAdapter<T>(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var resultsList: List<T> = ArrayList()

    fun updateWithNewList(newList: List<T>) {
        notifyItemRangeRemoved(0, resultsList.size)
        resultsList = newList
        notifyItemRangeInserted(0, newList.size)
    }

    fun getItem(index : Int): T {
        return resultsList[index]
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

}