package com.homeaway.viewmodel.venue.search

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class VenueSearchViewData @Inject constructor() {

    val emptyMessage = ObservableField<String>()
    val results = ObservableField<List<Any>>()
    val isLoading = ObservableBoolean()
    val isErrorLoading = ObservableBoolean()
    val isContentAvailable = ObservableBoolean()
    private val searchText = BehaviorSubject.create<String>()

    fun setSearchText(value: String) {
        searchText.onNext(value)
    }

    fun observeSearchText(): Observable<String> {
        return searchText.distinctUntilChanged()
    }

    fun getSearchText(): String {
        if (searchText.hasValue()) {
            return searchText.value!!
        } else {
            return ""
        }
    }

}