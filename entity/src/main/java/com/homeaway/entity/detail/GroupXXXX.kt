package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupXXXX(
    @Json(name = "count")
    val count: Int,
    @Json(name = "items")
    val items: List<ItemXXXX>,
    @Json(name = "name")
    val name: String,
    @Json(name = "summary")
    val summary: String,
    @Json(name = "type")
    val type: String
)