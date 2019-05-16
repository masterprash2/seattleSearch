package com.homeaway.entity.search

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VenuePage(
    @Json(name = "id")
    val id: String
)