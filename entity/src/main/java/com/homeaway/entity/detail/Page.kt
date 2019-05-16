package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Page(
    @Json(name = "pageInfo")
    val pageInfo: PageInfo,
    @Json(name = "user")
    val user: UserXXXX
)