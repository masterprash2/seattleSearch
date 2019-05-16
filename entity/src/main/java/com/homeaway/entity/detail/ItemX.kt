package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemX(
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "photo")
    val photo: PhotoX
)