package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VenueDetails(
    @Json(name = "meta")
    val meta: Meta,
    @Json(name = "response")
    val response: Response
)