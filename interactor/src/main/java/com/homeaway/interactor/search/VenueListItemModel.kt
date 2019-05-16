package com.homeaway.interactor.search

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.homeaway.gateway.FavoriteGateway

class VenueListItemModel(val id: String) {

    val name = ObservableField<String>()
    val category = ObservableField<String>()
    val distance = ObservableField<String>()
    val isFavorite = ObservableBoolean()

}