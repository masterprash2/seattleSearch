package com.homeaway.entity.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LabeledLatLng(
    @Json(name = "label")
    val label: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lng")
    val lng: Double
)