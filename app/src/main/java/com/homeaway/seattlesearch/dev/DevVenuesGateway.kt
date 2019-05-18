package com.homeaway.seattlesearch.dev

import android.content.Context
import android.net.Uri
import com.homeaway.entity.detail.VenueDetails
import com.homeaway.entity.photo.VenuePhotos
import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.gatewayimpl.R
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import okio.Buffer

class DevVenuesGateway(private val context: Context)  : VenuesGateway {

    private val clientId = "";//context.getString(R.string.foursquareApiClientId)
    private val clientSecret =""// context.getString(R.string.foursquareApiClientSecret)

    override fun getSearchResults(query: String): Observable<Response<SearchResults>> {
        return Observable.just(Response(success = true,response = getResponse()))
    }

    override fun getVenueDetail(venueId: String): Observable<Response<VenueDetails>> {
        return Observable.just(Response(success = true,response = getDetailResponse()))
    }

    override fun getPhoto(venueId: String): Observable<Response<VenuePhotos>> {
        return Observable.never()
    }

    override fun appendAuthQuery(url: String): String {
        if (url.isBlank()) {
            return url
        }
        return Uri.parse(url).buildUpon()
            .appendQueryParameter("clientId", clientId)
            .appendQueryParameter("clientSecret", clientSecret).build().toString()

    }

    fun getResponse(): SearchResults {
        val readFrom = Buffer().readFrom(context.assets.open("validsearch.json"))
        val build = Moshi.Builder().build()
        return build.adapter(SearchResults::class.java).fromJson(readFrom)!!
    }

    fun getDetailResponse(): VenueDetails {
        val readFrom = Buffer().readFrom(context.assets.open("validdetails.json"))
        val build = Moshi.Builder().build()
        return build.adapter(VenueDetails::class.java).fromJson(readFrom)!!
    }
}