package com.homeaway.gateway

import io.reactivex.Observable

interface FavoriteGateway {

    fun addToFavorites(venueId: String)
    fun venueFavoriteUpdates(): Observable<Boolean>

}