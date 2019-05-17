package com.homeaway.seattlesearch.activity.search

import com.homeaway.viewmodel.venue.search.VenueSearchNavigation
import javax.inject.Inject

class SearchNavigationImpl @Inject constructor(private val activity: SearchActivity) : VenueSearchNavigation {
    override fun openDetail(venueId: String) {
    }

    override fun showInMaps(query: String) {
    }

}