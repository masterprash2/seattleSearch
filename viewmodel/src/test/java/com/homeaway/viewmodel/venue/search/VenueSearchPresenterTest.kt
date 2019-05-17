package com.homeaway.viewmodel.venue.search

import com.homeaway.interactor.search.VenueListItemData
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class VenueSearchPresenterTest {

    lateinit var presenter: VenueSearchPresenter
    lateinit var viewData: VenueSearchViewData

    @Before
    fun setUp() {
        viewData = VenueSearchViewData()
        presenter = VenueSearchPresenter(viewData)
    }

    @Test
    fun freshLaunchCheck() {
        presenter.reset()
        assertFalse(viewData.isLoading.get())
        assertFalse(viewData.isErrorLoading.get())
        assertEquals("Search Venues", viewData.emptyMessage.get())
    }

    @Test
    fun checkLoader() {
        presenter.showLoading()
        assertTrue(viewData.isLoading.get())
        assertFalse(viewData.isErrorLoading.get())
    }

    @Test
    fun checkFailedFresh() {
        presenter.showError()
        assertFalse(viewData.isLoading.get())
        assertTrue(viewData.isErrorLoading.get())
        assertEquals("Network Error", viewData.emptyMessage.get())
    }

    @Test
    fun checkFailedWithPreExistingResult() {
        checkSuccessWithResults()
        checkFailedFresh()
    }

    @Test
    fun checkSuccessWithResults() {
        presenter.handleSuccess(createVenue())
        assertFalse(viewData.isLoading.get())
        assertFalse(viewData.isErrorLoading.get())
        assertEquals(1, viewData.getResults().size)
    }


    @Test
    fun checkSuccessWithoutResults() {
        viewData.setSearchText("Coffee")
        presenter.handleSuccess(Arrays.asList())
        assertFalse(viewData.isLoading.get())
        assertFalse(viewData.isErrorLoading.get())
        assertEquals(0, viewData.getResults().size)
        assertEquals("No results found", viewData.emptyMessage.get())
    }

    @Test
    fun testReset() {
        presenter.reset()
        assertFalse(viewData.isLoading.get())
        assertFalse(viewData.isErrorLoading.get())
        assertFalse(viewData.isContentAvailable.get())
        assertEquals(0,viewData.getResults().size)
        assertEquals("Search Venues", viewData.emptyMessage.get())
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
        return Arrays.asList(VenueListItemModel(createVenueModel()))
//
//        val readFrom = Buffer().readFrom(javaClass.classLoader.getResourceAsStream("valid.json"))
//        val build = Moshi.Builder().build()
//        return build.adapter(SearchResults::class.java).fromJson(readFrom)!!.response.venues
    }

    private fun createVenueModel(): VenueListItemData {
        return VenueListItemData(
            id = "anyId",
            category = "Category",
            name = "name",
            distance = "Distance"
        )
    }


    @After
    fun tearDown() {
    }
}