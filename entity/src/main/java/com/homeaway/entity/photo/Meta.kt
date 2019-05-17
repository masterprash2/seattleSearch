package com.homeaway.entity.photo


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "code")
    val code: Int,
    @Json(name = "requestId")
    val requestId: String
)