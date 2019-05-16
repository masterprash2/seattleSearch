package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sample(
    @Json(name = "entities")
    val entities: List<Entity>,
    @Json(name = "text")
    val text: String
)