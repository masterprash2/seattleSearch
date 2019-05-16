package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hours(
    @Json(name = "isLocalHoliday")
    val isLocalHoliday: Boolean,
    @Json(name = "isOpen")
    val isOpen: Boolean,
    @Json(name = "status")
    val status: String,
    @Json(name = "timeframes")
    val timeframes: List<TimeframeX>
)