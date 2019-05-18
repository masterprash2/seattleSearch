package com.homeaway.interactor

import com.homeaway.entity.detail.VenueDetails
import com.homeaway.gateway.MapsGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.detail.VenueDetailData
import io.reactivex.Observable
import javax.inject.Inject

class VenueDetailsInteractor @Inject constructor(
    private val venuesGateway: VenuesGateway,
    private val mapsGateway: MapsGateway
) {

    fun getVenueDeatils(venueId: String): Observable<Response<VenueDetailData>> {
        return venuesGateway.getVenueDetail(venueId).map { transform(it) }
    }

    private fun transform(input: Response<VenueDetails>): Response<VenueDetailData> {
        if (input.success && input.response != null) {
            return Response(true, transform(input.response!!))
        } else {
            return Response(false, null, input.exception)
        }
    }

    private fun transform(input: VenueDetails): VenueDetailData {
        val venue = input.response.venue
        return VenueDetailData(
            id = venue.id,
            name = venue.name,
            location = venue.location,
            mapImageUrl = mapsGateway.getMapsImageFromCenter(venue.location.lat, venue.location.lng),
            webLink = venue.shortUrl
        )
    }

}