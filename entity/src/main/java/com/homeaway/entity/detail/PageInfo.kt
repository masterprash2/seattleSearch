package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageInfo(
    @Json(name = "banner")
    val banner: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "links")
    val links: Links
)