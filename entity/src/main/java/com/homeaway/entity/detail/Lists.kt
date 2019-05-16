package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lists(
    @Json(name = "groups")
    val groups: List<GroupXXXXX>
)