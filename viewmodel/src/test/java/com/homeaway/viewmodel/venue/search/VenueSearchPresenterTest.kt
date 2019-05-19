package com.homeaway.viewmodel.venue.search

import com.homeaway.interactor.search.VenueListItemData
import com.homeaway.viewmodel.venue.search.item.ItemNavigation
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class VenueSearchPresenterTest {

    lateinit var presenter: VenueSearchPresenter
    lateinit var viewData: VenueSearchViewData
    lateinit var navigation: VenueSearchNavigation
    lateinit var itemNavigation: ItemNavigation

    @Before
    fun setUp() {
        itemNavigation = Mockito.mock(ItemNavigation::class.java)
        viewData = VenueSearchViewData()
        navigation = Mockito.mock(VenueSearchNavigation::class.java)
        presenter = VenueSearchPresenter(viewData, navigation)
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
        assertTrue(viewData.isContentAvailable.get())
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
        assertEquals(0, viewData.getResults().size)
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
        return Arrays.asList(VenueListItemModel(createVenueModel(), itemNavigation))
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
            distance = "Distance",
            photoUrl = ""
        )
    }


    @After
    fun tearDown() {
    }
}