package com.homeaway.gatewayimpl.retrofit

import com.homeaway.entity.detail.VenueDetails
import com.homeaway.entity.photo.VenuePhotos
import com.homeaway.entity.search.SearchResults
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoursquareApi {

    @GET("{VENUE_ID}")
    fun getVenueDetails(
        @Path("VENUE_ID") venueId: String,
        @Query("client_secret") clientSecret: String,
        @Query("client_id") clientId: String,
        @Query("v") version: String
    ): Observable<Response<VenueDetails>>

    @GET("search")
    fun searchVenues(
        @Query("client_secret") clientSecret: String,
        @Query("client_id") clientId: String,
        @Query("v") version: String,
        @QueryMap params: Map<String, String>
    ): Observable<Response<SearchResults>>

    @GET("{VENUE_ID}/photos")
    fun getPhotos(
        @Path("VENUE_ID") venueId: String,
        @Query("client_secret") clientSecret: String,
        @Query("client_id") clientId: String,
        @Query("v") version: String,
        @Query("limit") limit: String
    ): Observable<Response<VenuePhotos>>
}