package com.homeaway.viewmodel.venue.detail

import com.homeaway.entity.detail.Location
import com.homeaway.viewmodel.venue.BaseViewData
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class DetailViewData @Inject constructor() : BaseViewData() {

    private val venueMapLocation = BehaviorSubject.create<Location>()
    private val venueDetails = BehaviorSubject.create<List<DetailItemModel>>()
    private val isFavorite = BehaviorSubject.createDefault(false)
    var venueId : String = ""
    internal set

    internal fun setVenueDetails(value: List<DetailItemModel>) {
        venueDetails.onNext(value)
    }

    internal fun setVenueLocation(location: Location) {
        venueMapLocation.onNext(location)
    }

    internal fun setFavorite(isFavorite: Boolean) {
        this.isFavorite.onNext(isFavorite)
    }

    fun isFavorite(): Boolean {
        return isFavorite.value ?: false
    }

    fun observeFavoriteUpdates() : Observable<Boolean> {
        return isFavorite;
    }

    fun observeVenueLocation(): Observable<Location> {
        return venueMapLocation
    }

    fun getVenueDetails(): List<DetailItemModel> {
        if (venueDetails.hasValue()) {
            return venueDetails.value!!
        } else {
            return Arrays.asList()
        }
    }

    fun getVenueLocation(): Location? {
        return venueMapLocation.value
    }

    fun observeVenueDetails(): Observable<List<DetailItemModel>> {
        return venueDetails;
    }

}