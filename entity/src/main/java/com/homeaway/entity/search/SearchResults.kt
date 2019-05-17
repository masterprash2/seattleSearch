package com.homeaway.entity.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResults(
    @Json(name = "meta")
    val meta: Meta,
    @Json(name = "response")
    val response: Response
)