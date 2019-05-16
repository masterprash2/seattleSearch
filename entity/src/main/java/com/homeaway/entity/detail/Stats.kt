package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "checkinsCount")
    val checkinsCount: Int,
    @Json(name = "tipCount")
    val tipCount: Int,
    @Json(name = "usersCount")
    val usersCount: Int,
    @Json(name = "visitsCount")
    val visitsCount: Int
)