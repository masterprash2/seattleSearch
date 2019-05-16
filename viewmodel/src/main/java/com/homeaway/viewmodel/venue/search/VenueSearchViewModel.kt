package com.homeaway.viewmodel.venue.search

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.SearchInteractor
import com.homeaway.interactor.search.VenueListItemModel
import com.homeaway.viewmodel.venue.BaseViewModel
import com.homeaway.viewmodel.venue.ViewModelFactory
import io.reactivex.disposables.Disposable

@AutoFactory(implementing = [ViewModelFactory::class])
class VenueSearchViewModel(
    @Provided private val presenter: VenueSearchPresenter,
    @Provided private val searchInteractor: SearchInteractor
) : BaseViewModel() {

    private var searchDisposable : Disposable? = null

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
        searchDisposable = searchInteractor.search(value).subscribe {
            handleResponse(it)
        }
        add(searchDisposable!!)
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