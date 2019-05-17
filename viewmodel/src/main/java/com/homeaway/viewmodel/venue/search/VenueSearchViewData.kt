package com.homeaway.viewmodel.venue.search

import androidx.databinding.ObservableField
import com.homeaway.viewmodel.venue.BaseViewData
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class VenueSearchViewData @Inject constructor() : BaseViewData() {

    val emptyMessage = ObservableField<String>()
    private val results = BehaviorSubject.create<List<VenueListItemModel>>()
    private val searchText = BehaviorSubject.create<String>()

    internal fun setSearchText(value: String) {
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

    fun getResults(): List<VenueListItemModel> {
        return results.value ?: Arrays.asList()
    }

    internal fun setResults(value: List<VenueListItemModel>) {
        results.onNext(value)
    }

    fun observeResults(): Observable<List<VenueListItemModel>> {
        return results
    }

}