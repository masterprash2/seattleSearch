package com.homeaway.viewmodel.venue.detail

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.homeaway.interactor.VenueDetailsInteractor
import com.homeaway.interactor.detail.VenueDetailData
import com.homeaway.viewmodel.venue.BaseViewModel
import com.homeaway.viewmodel.venue.ViewModelFactory
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import com.homeaway.viewmodel.venue.detail.item.DetailItemNavigation
import com.homeaway.viewmodel.venue.detail.item.Type
import io.reactivex.disposables.Disposable
import java.util.*

@AutoFactory(implementing = [ViewModelFactory::class])
class DetailViewModel(
    @Provided private val presenter: DetailPresenter,
    @Provided private val detailInteractor: VenueDetailsInteractor,
    @Provided private val navigation: DetailViewNavigation
) : BaseViewModel() {

    private var detailRequest: Disposable? = null

    private val itemNavigation = object : DetailItemNavigation {

        override fun openWebLink(url: String) {
            navigation.openWebLink(url)
        }
    }

    fun loadDetails(venueId: String) {
        presenter.showLoading()
        detailRequest?.dispose()
        detailRequest = detailInteractor.getVenueDeatils(venueId).subscribe {
            if (it.success && it.response != null)
                handleResponse(it.response!!)
            else {
                presenter.showError()
            }
        }
        add(detailRequest!!)
    }

    private fun handleResponse(venueDetailData: VenueDetailData) {
        val list = ArrayList<DetailItemModel>()
        list.add(DetailItemModel("Name:", venueDetailData.name, Type.KEY_VALUE, itemNavigation))
        list.add(DetailItemModel("Address:", venueDetailData.toDisplayAddress(), Type.KEY_VALUE, itemNavigation))
        if (!venueDetailData.webLink.isNullOrEmpty())
            list.add(DetailItemModel("Web Link:", venueDetailData.webLink!!, Type.WEB_LINK, itemNavigation))
        list.add(DetailItemModel("Description:", venueDetailData.description, Type.DESCRIPTION, itemNavigation))
        presenter.handleSuccess(list,venueDetailData.mapImageUrl)
    }

    fun viewData() : DetailViewData {
        return presenter.viewData
    }


}