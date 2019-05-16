package com.homeaway.entity.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "categories")
    val categories: List<Category>,
    @Json(name = "id")
    val id: String,
    @Json(name = "location")
    val location: Location,
    @Json(name = "name")
    val name: String,
    @Json(name = "venuePage")
    val venuePage: VenuePage
)