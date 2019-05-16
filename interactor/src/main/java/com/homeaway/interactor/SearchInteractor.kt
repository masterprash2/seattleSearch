package com.homeaway.interactor

import com.homeaway.entity.search.SearchResults
import com.homeaway.entity.search.Venue
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.search.VenueListItemModel
import io.reactivex.Observable

class SearchInteractor(
    private val venuesGateway: VenuesGateway,
    private val locationGateway: LocationGateway
) {

    fun search(query: String): Observable<Response<List<VenueListItemModel>>> {
        return venuesGateway.getSearchResults(query).map { transform(it) }
    }

    private fun transform(response: Response<SearchResults>): Response<List<VenueListItemModel>> {
        val transformVenues =
            if (response.response != null) transformVenues(response.response!!.response.venues) else null
        return Response(response.success, transformVenues)
    }

    private fun transformVenues(venues: List<Venue>): List<VenueListItemModel> {
        val list = ArrayList<VenueListItemModel>(venues.size)
        for (venue in venues) {
            list.add(map(venue))
        }
        return list
    }

    private fun map(venue: Venue): VenueListItemModel {
        val item = VenueListItemModel(venue.id)
        val category = venue.categories.first();
        item.category.set(category.name)
        item.name.set(venue.name)

        val calculateDistance =
            locationGateway.calculateDistance(47.6062, 122.3321, venue.location.lat, venue.location.lng)
        item.distance.set(calculateDistance.toString())
        return item
    }

}