package com.homeaway.viewmodel.venue.search

import com.homeaway.viewmodel.venue.BasePresenter
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel
import java.util.*
import javax.inject.Inject

class VenueSearchPresenter @Inject constructor(
    private val venueSearchViewData: VenueSearchViewData,
    private val venueSearchNavigation: VenueSearchNavigation
) :
    BasePresenter<VenueSearchViewData>(venueSearchViewData) {

    override fun showLoading() {
        super.showLoading()
        updateEmptyResponseText()
    }

    override fun showError() {
        super.showError()
        updateEmptyResponseText()
    }

    fun handleSuccess(resultsList: List<VenueListItemModel>) {
        venueSearchViewData.isErrorLoading.set(false)
        venueSearchViewData.isLoading.set(false)
        venueSearchViewData.setResults(resultsList)
        venueSearchViewData.isContentAvailable.set(resultsList.isNotEmpty())
        updateEmptyResponseText()
    }


    private fun updateEmptyResponseText() {
        if (venueSearchViewData.isErrorLoading.get()) {
            venueSearchViewData.emptyMessage.set("Network Error")
        } else if (venueSearchViewData.getSearchText().isBlank()) {
            venueSearchViewData.emptyMessage.set("Search Venues")
        } else if (!venueSearchViewData.isContentAvailable.get()) {
            venueSearchViewData.emptyMessage.set("No results found")
        }
    }

    fun reset() {
        venueSearchViewData.setResults(Arrays.asList())
        venueSearchViewData.setSearchText("")
        venueSearchViewData.isLoading.set(false)
        venueSearchViewData.isErrorLoading.set(false)
        venueSearchViewData.isContentAvailable.set(false)
        updateEmptyResponseText()
    }

    fun updateSearchText(value: String) {
        venueSearchViewData.setSearchText(value)
    }

    fun navigateToDetail(venueId: String) {
        venueSearchNavigation.openDetail(venueId)
    }

    fun navigateToMaps() {
        venueSearchNavigation.showInMaps(viewData.getSearchText())
    }

}