package com.homeaway.viewmodel.venue.detail

import com.homeaway.entity.detail.VenueDetails
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.VenueDetailsInteractor
import com.homeaway.viewmodel.venue.detail.item.Type
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import okio.Buffer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class DetailViewModelTest {

    lateinit var viewModel: DetailViewModel
    lateinit var presenter: DetailPresenter
    lateinit var data: DetailViewData
    lateinit var interactor: VenueDetailsInteractor
    lateinit var venuesGateway: VenuesGateway
    lateinit var navigation: DetailViewNavigation

    @Before
    fun setUp() {
        venuesGateway = Mockito.mock(VenuesGateway::class.java)
        interactor = VenueDetailsInteractor(venuesGateway)
        data = DetailViewData()
        presenter = DetailPresenter(data)
        navigation = Mockito.mock(DetailViewNavigation::class.java)
        viewModel = DetailViewModel(presenter, interactor,navigation)
    }


    @Test
    fun loading() {
        whenever(venuesGateway.getVenueDetail("venueId")).thenReturn(Observable.never())
        viewModel.loadDetails("venueId")
        assertFalse(data.isContentAvailable.get())
        assertTrue(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @Test
    fun failed() {
        whenever(venuesGateway.getVenueDetail("venueId")).thenReturn(
            Observable.just(
                Response<VenueDetails>(
                    false,
                    null
                )
            )
        )
        viewModel.loadDetails("venueId")
        assertTrue(data.isErrorLoading.get())
        assertFalse(data.isLoading.get())
        assertFalse(data.isContentAvailable.get())
    }

    @Test
    fun success() {
        val response = getResponse()
        whenever(venuesGateway.getVenueDetail("venueId")).thenReturn(Observable.just(Response(true, response)))
        viewModel.loadDetails("venueId")
        assertFalse(data.isErrorLoading.get())
        assertFalse(data.isLoading.get())
        assertTrue(data.isContentAvailable.get())
        val venueDetails = data.getVenueDetails()
        assertEquals(3, venueDetails.size)

        assertEquals("Leschi House Concerts", venueDetails[0].value)
        assertEquals(Type.KEY_VALUE, venueDetails[0].type)

        assertEquals("Addr1, Addr2, Addr3", venueDetails[1].value)
        assertEquals(Type.KEY_VALUE, venueDetails[1].type)

        assertEquals("http://weblink", venueDetails[2].value)
        assertEquals(Type.WEB_LINK, venueDetails[2].type)

//        assertEquals("venueMapImageUrl", data.venueMapImage.get())
    }

    @Test
    fun webLinkNavigation() {
        success()
        val weblinkItem = data.getVenueDetails()[2]
        weblinkItem.performClick()
        verify(navigation).openWebLink(weblinkItem.value)
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