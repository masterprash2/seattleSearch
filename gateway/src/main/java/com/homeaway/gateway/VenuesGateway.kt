package com.homeaway.gateway

import com.homeaway.entity.detail.VenueDetails
import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.data.Response
import io.reactivex.Observable

interface VenuesGateway {

    fun getSearchResults(query: String): Observable<Response<SearchResults>>
    fun getVenueDetail(venueId: String): Observable<Response<VenueDetails>>

}