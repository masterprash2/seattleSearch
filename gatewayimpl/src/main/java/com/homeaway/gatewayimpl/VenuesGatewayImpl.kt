package com.homeaway.gatewayimpl

import android.content.Context
import com.homeaway.entity.detail.VenueDetails
import com.homeaway.entity.photo.VenuePhotos
import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.gatewayimpl.retrofit.FoursquareApi
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class VenuesGatewayImpl @Inject constructor(
    private val foursquareApi: FoursquareApi,
    context: Context,
    private val backgroundThread: Scheduler
) : VenuesGateway {

    private val clientId = context.getString(R.string.foursquareApiClientId)
    private val clientSecret = context.getString(R.string.foursquareApiClientSecret)
    private val apiVersion = context.getString(R.string.foursquareApiVersion)


    override fun getSearchResults(query: String): Observable<Response<SearchResults>> {
        val map = HashMap<String, String>()
        map.put("near", "Seattle,+WA")
        map.put("query", query)
        map.put("limit", "20")
        return foursquareApi.searchVenues(
            clientId = clientId,
            clientSecret = clientSecret, version = apiVersion,
            params = map
        ).subscribeOn(backgroundThread)
            .map { transform(it) }
            .onErrorReturn { Response(false, null, it) }
    }


    override fun getVenueDetail(venueId: String): Observable<Response<VenueDetails>> {
        return foursquareApi.getVenueDetails(
            venueId = venueId,
            clientSecret = clientSecret,
            clientId = clientId,
            version = apiVersion
        ).subscribeOn(backgroundThread)
            .map { transform(it) }
            .onErrorReturn { Response(false, null, it) }
    }

    override fun getPhoto(venueId: String): Observable<Response<VenuePhotos>> {
        return foursquareApi.getPhotos(venueId, clientSecret, clientId, apiVersion, "1")
            .subscribeOn(backgroundThread)
            .map { transform(it) }
            .onErrorReturn { Response(false, null, it) }
    }


    private fun <T> transform(it: retrofit2.Response<T>): Response<T> {
        return Response(it.isSuccessful, it.body())
    }


}