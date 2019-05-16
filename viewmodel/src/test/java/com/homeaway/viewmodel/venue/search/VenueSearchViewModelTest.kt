package com.homeaway.viewmodel.venue.search

import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.SearchInteractor
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class VenueSearchViewModelTest {

    lateinit var presenter: VenueSearchPresenter
    lateinit var viewData: VenueSearchViewData
    lateinit var viewModel: VenueSearchViewModel
    lateinit var searchInteractor: SearchInteractor
    lateinit var gateway: VenuesGateway
    lateinit var locationGateway: LocationGateway

    @Before
    fun setUp() {
        viewData = VenueSearchViewData()
        locationGateway = Mockito.mock(LocationGateway::class.java)
        whenever(locationGateway.calculateDistance(any(), any(), any(), any())).thenReturn(123.0)
        gateway = Mockito.mock(VenuesGateway::class.java)
        searchInteractor = SearchInteractor(gateway, locationGateway)
        presenter = VenueSearchPresenter(viewData)
        viewModel = VenueSearchViewModel(presenter, searchInteractor)

    }

    @Test
    fun typeaheadSearch() {
        whenever(gateway.getSearchResults("coffee")).thenReturn(Observable.never())
        assertFalse(viewData.isLoading.get())
        viewData.setSearchText("coffee")
        assertTrue(viewData.isLoading.get())
    }

    @Test
    fun typeaheadTextClear() {
        typeaheadSearch()
        viewData.setSearchText("")
        assertFalse(viewData.isLoading.get())
    }

    @Test
    fun testRetryAfterFailure() {
        whenever(gateway.getSearchResults("coffee")).thenReturn(Observable.just(Response<SearchResults>(false, null)))
        viewData.setSearchText("coffee")
        assertFalse(viewData.isLoading.get())
        assertTrue(viewData.isErrorLoading.get())
        assertNull(viewData.results.get())
        whenever(gateway.getSearchResults("coffee")).thenReturn(Observable.never())
        viewModel.retry()
        assertTrue(viewData.isLoading.get())
        assertFalse(viewData.isErrorLoading.get())
    }

    @Test
    fun testSingleSearchForMultipleText() {
        val firstPublisher = PublishSubject.create<Response<SearchResults>>()
        whenever(gateway.getSearchResults("coffee")).thenReturn(firstPublisher)
        viewModel.setSearchText("coffee")
        assertFalse(viewData.isErrorLoading.get())
        assertTrue(viewData.isLoading.get())

        val secondPublisher = PublishSubject.create<Response<SearchResults>>()
        whenever(gateway.getSearchResults("chocolate")).thenReturn(secondPublisher)
        viewModel.setSearchText("chocolate")
        assertFalse(viewData.isErrorLoading.get())
        assertTrue(viewData.isLoading.get())

        // First published response should not affect the viewdata state
        firstPublisher.onNext(Response(false,null))
        assertFalse(viewData.isErrorLoading.get())
        assertTrue(viewData.isLoading.get())

        //Second publisher's response should update the UI
        firstPublisher.onNext(Response(false,null))
        assertFalse(viewData.isErrorLoading.get())
        assertTrue(viewData.isLoading.get())
    }

    @After
    fun tearDown() {
    }
}