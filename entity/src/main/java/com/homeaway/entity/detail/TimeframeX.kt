package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeframeX(
    @Json(name = "open")
    val `open`: List<OpenX>,
    @Json(name = "days")
    val days: String,
    @Json(name = "includesToday")
    val includesToday: Boolean,
    @Json(name = "segments")
    val segments: List<Any>
)