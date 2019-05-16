package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXXX(
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "prefix")
    val prefix: String,
    @Json(name = "source")
    val source: SourceX,
    @Json(name = "suffix")
    val suffix: String,
    @Json(name = "user")
    val user: UserXXX,
    @Json(name = "visibility")
    val visibility: String,
    @Json(name = "width")
    val width: Int
)