package com.homeaway.viewmodel.venue.detail

import com.homeaway.entity.detail.Location
import com.homeaway.viewmodel.venue.BasePresenter
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val data: DetailViewData) : BasePresenter<DetailViewData>(data) {


    internal fun handleSuccess(venueDetailData: List<DetailItemModel>,
        location: Location
    ) {
        data.isErrorLoading.set(false)
        data.isLoading.set(false)
        data.isContentAvailable.set(true)
        data.setVenueDetails(venueDetailData)
        data.setVenueLocation(location)
    }

    internal fun updateVenueId(venueId : String) {
        data.venueId = venueId
    }

    internal fun updateFavoriteStatus(it: Boolean) {
        data.setFavorite(it)
    }


}