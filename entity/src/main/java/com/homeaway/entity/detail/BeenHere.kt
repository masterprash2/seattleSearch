package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BeenHere(
    @Json(name = "count")
    val count: Int,
    @Json(name = "lastCheckinExpiredAt")
    val lastCheckinExpiredAt: Int,
    @Json(name = "marked")
    val marked: Boolean,
    @Json(name = "unconfirmedCount")
    val unconfirmedCount: Int
)