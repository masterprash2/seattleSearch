package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXXXX(
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayValue")
    val displayValue: String
)