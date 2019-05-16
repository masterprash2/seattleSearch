package com.homeaway.viewmodel.venue.search.item

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.homeaway.interactor.search.VenueListItemData

class VenueListItemModel(private val data: VenueListItemData) {

    val name = ObservableField<String>(data.name)
    val category = ObservableField<String>(data.category)
    val distance = ObservableField<String>(data.distance)
    val isFavorite = ObservableBoolean()

}