package com.homeaway.interactor

import com.homeaway.entity.detail.VenueDetails
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.detail.VenueDetailData
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import okio.Buffer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class VenueDetailsInteractorTest {

    lateinit var detailsInteractor: VenueDetailsInteractor
    lateinit var venuesGateway: VenuesGateway

    @Before
    fun setUp() {
        venuesGateway = Mockito.mock(VenuesGateway::class.java)
        detailsInteractor = VenueDetailsInteractor(venuesGateway)
    }

    @Test
    fun testFailedResponse() {
        whenever(venuesGateway.getVenueDetail("venueId")).thenReturn(
            Observable.just(Response<VenueDetails>(false, null))
        )

        val observe = TestObserver<Response<VenueDetailData>>()
        detailsInteractor.getVenueDeatils("venueId").subscribe(observe)
        observe.assertValueCount(1)
        val response = observe.values().first();
        assertFalse(response.success)
    }

    @Test
    fun testSuccessResponse() {
        val networkResp = getResponse();
        whenever(venuesGateway.getVenueDetail("venueId")).thenReturn(
            Observable.just(Response<VenueDetails>(true, networkResp))
        )

        val observe = TestObserver<Response<VenueDetailData>>()
        detailsInteractor.getVenueDeatils("venueId").subscribe(observe)
        observe.assertValueCount(1)
        val response = observe.values().first();
        assertTrue(response.success)
        val venue = networkResp.response.venue
        assertEquals(venue.name,response.response!!.name)
        assertEquals(venue.contact,response.response!!.contact)
        assertEquals(venue.location,response.response!!.location)
        assertEquals(venue.description,response.response!!.description)
        assertEquals(venue.url,response.response!!.webLink)
        assertEquals(venue.id,response.response!!.id)
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