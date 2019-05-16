package com.homeaway.viewmodel.venue.search

import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.SearchInteractor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class VenueSearchViewModelTest {

    lateinit var presenter: VenueSearchPresenter
    lateinit var data: VenueSeachData
    lateinit var viewModel: VenueSearchViewModel
    lateinit var searchInteractor: SearchInteractor
    lateinit var gateway: VenuesGateway
    lateinit var locationGateway: LocationGateway

    @Before
    fun setUp() {
        data = VenueSeachData()
        locationGateway = Mockito.mock(LocationGateway::class.java)
        whenever(locationGateway.calculateDistance(any(), any(), any(), any())).thenReturn(123.0)
        gateway = Mockito.mock(VenuesGateway::class.java)
        searchInteractor = SearchInteractor(gateway, locationGateway)
        presenter = VenueSearchPresenter(data)
        viewModel = VenueSearchViewModel(presenter, searchInteractor)

    }

    @Test
    fun typeaheadSearch() {
        whenever(gateway.getSearchResults("coffee")).thenReturn(Observable.never())
        assertFalse(data.isLoading.get())
        data.searchText.set("coffee")
        assertTrue(data.isLoading.get())
    }

    @Test
    fun typeaheadTextClear() {
        typeaheadSearch()
        data.searchText.set(null)
        assertFalse(data.isLoading.get())
    }

    @Test
    fun testRetryAfterFailure() {
        whenever(gateway.getSearchResults("coffee")).thenReturn(Observable.just(Response<SearchResults>(false, null)))
        data.searchText.set("coffee")
        assertFalse(data.isLoading.get())
        assertTrue(data.isErrorLoading.get())
        assertNull(data.results.get())
        whenever(gateway.getSearchResults("coffee")).thenReturn(Observable.never())
        viewModel.retry()
        assertTrue(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @After
    fun tearDown() {
    }
}