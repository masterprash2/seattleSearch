package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entity(
    @Json(name = "indices")
    val indices: List<Int>,
    @Json(name = "type")
    val type: String
)