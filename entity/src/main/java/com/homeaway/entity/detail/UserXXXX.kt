package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserXXXX(
    @Json(name = "bio")
    val bio: String,
    @Json(name = "contact")
    val contact: Contact,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "homeCity")
    val homeCity: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "lists")
    val lists: Lists,
    @Json(name = "photo")
    val photo: PhotoXXXXXX,
    @Json(name = "tips")
    val tips: TipsX,
    @Json(name = "type")
    val type: String
)