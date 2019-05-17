package com.homeaway.entity.photo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photos(
    @Json(name = "count")
    val count: Int,
    @Json(name = "dupesRemoved")
    val dupesRemoved: Int,
    @Json(name = "items")
    val items: List<Item>
)