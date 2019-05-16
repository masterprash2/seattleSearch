package com.homeaway.viewmodel.venue.search

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import java.util.*

class VenueSearchViewModel(private val presenter: VenueSearchPresenter) : ViewModel() {


    init {
        watchSearchText()
    }

    private fun watchSearchText() {
        presenter.venueSeachData.searchText.addOnPropertyChangedCallback( object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                search(presenter.venueSeachData.searchText.get())
            }
        })
    }

    private fun search(value: String?) {
        if(value.isNullOrBlank()) {
            presenter.reset()
        }
        else {
            requestSearch(value)
        }
    }

    private fun requestSearch(value : String) {
        presenter.showLoading()
    }

    fun retry() {
        search(presenter.venueSeachData.searchText.get())
    }

}