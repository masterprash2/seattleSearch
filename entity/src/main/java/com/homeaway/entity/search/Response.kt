package com.homeaway.entity.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "venues")
    val venues: List<Venue>
)