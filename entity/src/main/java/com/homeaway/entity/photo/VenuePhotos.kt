package com.homeaway.entity.photo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VenuePhotos(
    @Json(name = "meta")
    val meta: Meta,
    @Json(name = "response")
    val response: Response
)