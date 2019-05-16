package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoXXX(
    @Json(name = "prefix")
    val prefix: String,
    @Json(name = "suffix")
    val suffix: String
)