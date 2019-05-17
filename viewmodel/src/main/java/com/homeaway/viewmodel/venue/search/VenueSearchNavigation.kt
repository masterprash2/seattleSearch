package com.homeaway.viewmodel.venue.search

interface VenueSearchNavigation {

    fun openDetail(venueId : String)
    fun showInMaps(query : String)

}