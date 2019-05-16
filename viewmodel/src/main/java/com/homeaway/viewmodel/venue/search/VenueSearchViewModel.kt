package com.homeaway.viewmodel.venue.search

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.SearchInteractor
import com.homeaway.interactor.search.VenueListItemData
import com.homeaway.viewmodel.venue.BaseViewModel
import com.homeaway.viewmodel.venue.ViewModelFactory
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel
import io.reactivex.disposables.Disposable

@AutoFactory(implementing = [ViewModelFactory::class])
class VenueSearchViewModel(
    @Provided private val presenter: VenueSearchPresenter,
    @Provided private val searchInteractor: SearchInteractor
) : BaseViewModel() {

    private var searchDisposable: Disposable? = null

    init {
        add(getViewData().observeSearchText().subscribe {
            searchDisposable?.dispose()
            search(it)
        })
    }

    fun setSearchText(value: String) {
        presenter.updateSearchText(value)
    }

    private fun search(value: String) {
        if (value.isBlank()) {
            presenter.reset()
        } else {
            requestSearch(value)
        }
    }

    private fun requestSearch(value: String) {
        presenter.showLoading()
        searchDisposable?.dispose()
        searchDisposable = searchInteractor.search(value).map { transform(it) }.subscribe {
            handleResponse(it)
        }
        add(searchDisposable!!)
    }

    private fun transform(response: Response<List<VenueListItemData>>): Response<List<VenueListItemModel>> {
        if (response.success && response.response != null) {
            return Response(true, transform(response.response!!))
        } else {
            return Response(false, null, response.exception)
        }
    }

    private fun transform(data: List<VenueListItemData>): List<VenueListItemModel> {
        val list = ArrayList<VenueListItemModel>()
        for (venue in data) {
            list.add(VenueListItemModel(venue))
        }
        return list
    }

    private fun handleResponse(it: Response<List<VenueListItemModel>>) {
        if (it.success) {
            presenter.handleSuccess(it.response!!)
        } else {
            presenter.showError()
        }

    }

    fun retry() {
        search(presenter.viewData.getSearchText())
    }


    fun getViewData(): VenueSearchViewData {
        return presenter.viewData
    }
}