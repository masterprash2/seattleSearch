package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contact(
    @Json(name = "facebook")
    val facebook: String,
    @Json(name = "twitter")
    val twitter: String
)