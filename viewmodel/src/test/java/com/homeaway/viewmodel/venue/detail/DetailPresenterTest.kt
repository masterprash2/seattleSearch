package com.homeaway.viewmodel.venue.detail

import com.homeaway.entity.detail.VenueDetails
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import com.squareup.moshi.Moshi
import okio.Buffer
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
        val location = getResponse().response.venue.location;
        val createDummyResponse = createDummyResponse()
        presenter.handleSuccess(createDummyResponse, location)
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
        assertTrue(data.getVenueDetails() == createDummyResponse)
        assertEquals(location,data.getVenueLocation())
    }


    private fun createDummyResponse(): List<DetailItemModel> {
        return Arrays.asList()
    }


    fun getResponse(): VenueDetails {
        val readFrom = Buffer().readFrom(javaClass.classLoader.getResourceAsStream("validdetails.json"))
        val build = Moshi.Builder().build()
        return build.adapter(VenueDetails::class.java).fromJson(readFrom)!!
    }


    @After
    fun tearDown() {
    }
}