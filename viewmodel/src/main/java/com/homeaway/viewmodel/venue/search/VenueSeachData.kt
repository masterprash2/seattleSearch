package com.homeaway.viewmodel.venue.search

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField

class VenueSeachData {

    val emptyMessage = ObservableField<String>()
    val results = ObservableField<List<Any>>()
    val searchText = ObservableField<String>()
    val isLoading = ObservableBoolean()
    val isErrorLoading = ObservableBoolean()
    val isContentAvailable = ObservableBoolean()


}