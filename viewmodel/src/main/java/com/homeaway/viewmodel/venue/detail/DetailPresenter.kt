package com.homeaway.viewmodel.venue.detail

import com.homeaway.viewmodel.venue.BasePresenter
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val data: DetailViewData) : BasePresenter<DetailViewData>(data) {


    fun handleSuccess() {
        data.isErrorLoading.set(false)
        data.isLoading.set(false)
        data.isContentAvailable.set(true)
    }

}