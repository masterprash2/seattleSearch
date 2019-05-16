package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Inbox(
    @Json(name = "count")
    val count: Int,
    @Json(name = "items")
    val items: List<Any>
)