package com.homeaway.gateway

import io.reactivex.Observable

interface FavoriteGateway {

    fun addToFavorites(venueId: String)
    fun removeFromFavorites(venueId: String)
    fun venueFavoriteUpdates(venueId: String): Observable<Boolean>

}