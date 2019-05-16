package com.homeaway.viewmodel.venue.search

import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class VenueSearchViewModelTest {

    lateinit var presenter: VenueSearchPresenter
    lateinit var data: VenueSeachData
    lateinit var viewModel: VenueSearchViewModel

    @Before
    fun setUp() {
        data = VenueSeachData()
        presenter = VenueSearchPresenter(data)
        viewModel = VenueSearchViewModel(presenter)
    }

    @Test
    fun typeaheadSearch() {
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
        typeaheadSearch()
        presenter.responseFailed()
        assertFalse(data.isLoading.get())
        assertTrue(data.isErrorLoading.get())
        assertNull(data.results.get())
        viewModel.retry()
        assertTrue(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @After
    fun tearDown() {
    }
}