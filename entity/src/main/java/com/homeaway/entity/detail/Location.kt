package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "cc")
    val cc: String = "",
    @Json(name = "city")
    val city: String = "",
    @Json(name = "country")
    val country: String = "",
    @Json(name = "formattedAddress")
    val formattedAddress: List<String> = listOf(),
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lng")
    val lng: Double = 0.0,
    @Json(name = "state")
    val state: String = ""
)