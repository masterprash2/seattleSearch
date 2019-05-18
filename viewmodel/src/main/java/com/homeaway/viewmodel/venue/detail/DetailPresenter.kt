package com.homeaway.viewmodel.venue.detail

import com.homeaway.viewmodel.venue.BasePresenter
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val data: DetailViewData) : BasePresenter<DetailViewData>(data) {


    fun handleSuccess(venueDetailData: List<DetailItemModel>) {
        data.isErrorLoading.set(false)
        data.isLoading.set(false)
        data.isContentAvailable.set(true)
        data.setVenueDetails(venueDetailData)
    }


}