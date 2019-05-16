package com.homeaway.interactor

import com.homeaway.entity.search.SearchResults
import com.homeaway.entity.search.Venue
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.search.VenueListItemData
import io.reactivex.Observable
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val venuesGateway: VenuesGateway,
    private val locationGateway: LocationGateway
) {

    fun search(query: String): Observable<Response<List<VenueListItemData>>> {
        return venuesGateway.getSearchResults(query).map { transform(it) }
    }

    private fun transform(response: Response<SearchResults>): Response<List<VenueListItemData>> {
        val transformVenues =
            if (response.response != null) transformVenues(response.response!!.response.venues) else null
        return Response(response.success, transformVenues)
    }

    private fun transformVenues(venues: List<Venue>): List<VenueListItemData> {
        val list = ArrayList<VenueListItemData>(venues.size)
        for (venue in venues) {
            list.add(map(venue))
        }
        return list
    }

    private fun map(venue: Venue): VenueListItemData {
        val category = venue.categories.first();
        val distance =
            locationGateway.calculateDistance(47.6062, 122.3321, venue.location.lat, venue.location.lng)
        return VenueListItemData(
            id = venue.id,
            category = category.name,
            name = venue.name,
            distance = distance.toString()
        )
    }

}