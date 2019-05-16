package com.homeaway.viewmodel.venue.search

import com.homeaway.interactor.search.VenueListItemModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class VenueSearchPresenterTest {

    lateinit var presenter: VenueSearchPresenter
    lateinit var data: VenueSeachData

    @Before
    fun setUp() {
        data = VenueSeachData()
        presenter = VenueSearchPresenter(data)
    }

    @Test
    fun freshLaunchCheck() {
        presenter.reset()
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
        assertEquals("Search Venues", data.emptyMessage.get())
    }

    @Test
    fun checkLoader() {
        presenter.showLoading()
        assertTrue(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @Test
    fun checkFailedFresh() {
        presenter.responseFailed()
        assertFalse(data.isLoading.get())
        assertTrue(data.isErrorLoading.get())
        assertEquals("Network Error", data.emptyMessage.get())
    }

    @Test
    fun checkFailedWithPreExistingResult() {
        checkSuccessWithResults()
        checkFailedFresh()
    }

    @Test
    fun checkSuccessWithResults() {
        presenter.handleSuccess(createVenue())
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
        assertEquals(1, data.results.get()?.size)
    }


    @Test
    fun checkSuccessWithoutResults() {
        data.searchText.set("Coffee")
        presenter.handleSuccess(Arrays.asList())
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
        assertEquals(0, data.results.get()?.size)
        assertEquals("No results found", data.emptyMessage.get())
    }

    @Test
    fun testReset() {
        presenter.reset()
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
        assertFalse(data.isContentAvailable.get())
        assertNull(data.results.get())
        assertEquals("Search Venues", data.emptyMessage.get())
    }

    @Test
    fun testResetAfterSuccessResponse() {
        checkSuccessWithResults()
        testReset()
    }

    @Test
    fun testResetAfterFailedReponse() {
        checkFailedWithPreExistingResult()
        testReset()
    }


    private fun createVenue(): List<VenueListItemModel> {
        return Arrays.asList(createVenueModel())
//
//        val readFrom = Buffer().readFrom(javaClass.classLoader.getResourceAsStream("valid.json"))
//        val build = Moshi.Builder().build()
//        return build.adapter(SearchResults::class.java).fromJson(readFrom)!!.response.venues
    }

    private fun createVenueModel(): VenueListItemModel {
        return VenueListItemModel("anyId").apply {
            category.set("Category")
            name.set("name")
            distance.set("Distance")
            isFavorite.set(true)

        }
    }


    @After
    fun tearDown() {
    }
}