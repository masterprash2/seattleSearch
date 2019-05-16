package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserXXX(
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "lastName")
    val lastName: String
)