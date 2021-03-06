package com.homeaway.entity.photo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "checkin")
    val checkin: Checkin,
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "prefix")
    val prefix: String,
    @Json(name = "suffix")
    val suffix: String,
    @Json(name = "width")
    val width: Int
)