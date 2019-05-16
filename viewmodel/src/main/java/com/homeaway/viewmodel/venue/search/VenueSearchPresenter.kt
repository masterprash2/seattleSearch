package com.homeaway.viewmodel.venue.search

class VenueSearchPresenter(val venueSeachData: VenueSeachData) {

    fun showLoading() {
        venueSeachData.isErrorLoading.set(false)
        venueSeachData.isLoading.set(true)
        updateEmptyResponseText()
    }

    fun responseFailed() {
        venueSeachData.isErrorLoading.set(true)
        venueSeachData.isLoading.set(false)
        updateEmptyResponseText()
    }

    fun handleSuccess(resultsList: List<Any>) {
        venueSeachData.isErrorLoading.set(false)
        venueSeachData.isLoading.set(false)
        venueSeachData.results.set(resultsList)
        venueSeachData.isContentAvailable.set(false)
        updateEmptyResponseText()
    }

    private fun updateEmptyResponseText() {
        if(venueSeachData.isErrorLoading.get()) {
            venueSeachData.emptyMessage.set("Network Error")
        }
        else if(venueSeachData.searchText.get().isNullOrBlank()) {
            venueSeachData.emptyMessage.set("Search Venues")
        }
        else if(!venueSeachData.isContentAvailable.get()) {
            venueSeachData.emptyMessage.set("No results found")
        }
    }

    fun reset() {
        venueSeachData.searchText.set(null)
        updateEmptyResponseText()
    }

}