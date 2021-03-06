package com.homeaway.interactor

import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.interactor.search.VenueListItemData
import com.nhaarman.mockitokotlin2.any
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

class SearchInteractorTest {

    lateinit var venuesGateway: VenuesGateway
    lateinit var interactor: SearchInteractor
    lateinit var locationGateway: LocationGateway

    @Before
    fun setUp() {
        venuesGateway = Mockito.mock(VenuesGateway::class.java)
        whenever(venuesGateway.appendAuthQuery(any())).thenReturn("appendedPhotoUrl")
        locationGateway = Mockito.mock(LocationGateway::class.java)
        whenever(locationGateway.calculateDistanceFromCenter(any(), any())).thenReturn(1234.0)
        interactor = SearchInteractor(venuesGateway, locationGateway)
    }

    @Test
    fun success() {
        val observer = TestObserver<Response<List<VenueListItemData>>>()
        whenever(venuesGateway.getSearchResults("coffee")).thenReturn(
            Observable.just(
                Response<SearchResults>(
                    true,
                    getResponse()
                )
            )
        )
        interactor.search("coffee").subscribe(observer)
        observer.assertValueCount(1)
        val resp = observer.values().first();
        assertTrue(resp.success)
        assertNotNull(resp.response)
        assertEquals(1, resp.response!!.size)

        val itemViewModel = resp.response!!.first();
        assertEquals("Mr. Purple", itemViewModel.name)
        assertEquals("Hotel Bar", itemViewModel.category)
        assertEquals("5642aef9498e51025cf4a7a5", itemViewModel.id)

    }


    @Test
    fun failure() {
        val observer = TestObserver<Response<List<VenueListItemData>>>()
        whenever(venuesGateway.getSearchResults("coffee")).thenReturn(
            Observable.just(
                Response<SearchResults>(
                    false,
                    null
                )
            )
        )
        interactor.search("coffee").subscribe(observer)
        observer.assertValueCount(1)
        assertFalse(observer.values().first().success)
        assertNull(observer.values().first().response)
    }

    fun getResponse(): SearchResults {
        val readFrom = Buffer().readFrom(javaClass.classLoader.getResourceAsStream("validsearch.json"))
        val build = Moshi.Builder().build()
        return build.adapter(SearchResults::class.java).fromJson(readFrom)!!
    }


    @After
    fun tearDown() {
    }
}