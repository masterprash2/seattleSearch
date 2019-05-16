package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Listed(
    @Json(name = "count")
    val count: Int,
    @Json(name = "groups")
    val groups: List<Group>
)