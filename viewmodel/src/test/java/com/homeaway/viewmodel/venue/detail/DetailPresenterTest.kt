package com.homeaway.viewmodel.venue.detail

import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class DetailPresenterTest {

    lateinit var presenter: DetailPresenter
    lateinit var data: DetailViewData

    @Before
    fun setUp() {
        data = DetailViewData()
        presenter = DetailPresenter(data)
    }

    @Test
    fun handleLoading() {
        presenter.showLoading()
        assertTrue(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @Test
    fun handleErrorLoading() {
        presenter.showError()
        assertFalse(data.isLoading.get())
        assertTrue(data.isErrorLoading.get())
    }

    @Test
    fun handleSuccess() {
        val createDummyResponse = createDummyResponse()
        presenter.handleSuccess(createDummyResponse, "mapImageUrl")
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
        assertTrue(data.getVenueDetails() == createDummyResponse)
        assertEquals("mapImageUrl",data.venueMapImage.get())
    }


    private fun createDummyResponse(): List<DetailItemModel> {
        return Arrays.asList()
    }


    @After
    fun tearDown() {
    }
}