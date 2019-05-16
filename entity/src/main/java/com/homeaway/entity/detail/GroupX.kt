package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupX(
    @Json(name = "count")
    val count: Int,
    @Json(name = "items")
    val items: List<ItemXX>,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String
)