package com.homeaway.entity.photo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Checkin(
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "timeZoneOffset")
    val timeZoneOffset: Int,
    @Json(name = "type")
    val type: String
)