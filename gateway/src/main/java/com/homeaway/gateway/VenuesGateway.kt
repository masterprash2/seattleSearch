package com.homeaway.gateway

import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.data.Response
import io.reactivex.Observable

interface VenuesGateway {

    fun getSearchResults(query: String): Observable<Response<SearchResults>>

}