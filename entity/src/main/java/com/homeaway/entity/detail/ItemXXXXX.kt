package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXXXXX(
    @Json(name = "url")
    val url: String
)