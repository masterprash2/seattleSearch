package com.homeaway.interactor

import com.homeaway.entity.search.Category
import com.homeaway.entity.search.SearchResults
import com.homeaway.entity.search.Venue
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.search.VenueListItemData
import io.reactivex.Observable
import java.lang.StringBuilder
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
        val category: Category? = venue.categories.firstOrNull();
        val distance =
            locationGateway.calculateDistanceFromCenter(venue.location.lat, venue.location.lng)
        val photoUrl =
            if (category != null) category.icon.prefix + "100" + category.icon.suffix
            else ""

        return VenueListItemData(
            id = venue.id,
            category = category?.name ?: "",
            name = venue.name,
            distance = formatDistance(distance),
            photoUrl = venuesGateway.appendAuthQuery(photoUrl)
        )
    }

    private fun formatDistance(distance : Double) : String {
        val sb = StringBuilder()
        val km = (distance / 1000).toInt()
        if( km > 0 ) {
            sb.append(km).append("Km ")
        }
        val meters = (distance%1000).toInt()
        if(meters > 0) {
            sb.append(meters).append("m")
        }
        return sb.toString()
    }

}