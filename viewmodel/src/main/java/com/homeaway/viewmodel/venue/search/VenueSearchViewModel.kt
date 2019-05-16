package com.homeaway.viewmodel.venue.search

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.homeaway.entity.search.SearchResults
import com.homeaway.entity.search.Venue
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.SearchInteractor
import com.homeaway.interactor.search.VenueListItemModel
import io.reactivex.disposables.CompositeDisposable

class VenueSearchViewModel(
    private val presenter: VenueSearchPresenter,
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        watchSearchText()
    }

    private fun watchSearchText() {
        presenter.venueSeachData.searchText.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                search(presenter.venueSeachData.searchText.get())
            }
        })
    }

    private fun search(value: String?) {
        if (value.isNullOrBlank()) {
            presenter.reset()
        } else {
            requestSearch(value)
        }
    }

    private fun requestSearch(value: String) {
        presenter.showLoading()
        compositeDisposable.add(searchInteractor.search(value).subscribe {
            handleResponse(it)
        })
    }

    private fun handleResponse(it: Response<List<VenueListItemModel>>) {
        if(it.success) {
            presenter.handleSuccess(it.response!!)
        }
        else {
            presenter.responseFailed()
        }

    }

    fun retry() {
        search(presenter.venueSeachData.searchText.get())
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}