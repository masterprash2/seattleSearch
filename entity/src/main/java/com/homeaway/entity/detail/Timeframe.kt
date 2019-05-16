package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Timeframe(
    @Json(name = "open")
    val `open`: List<Open>,
    @Json(name = "days")
    val days: String,
    @Json(name = "segments")
    val segments: List<Any>
)