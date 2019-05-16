package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "public")
    val `public`: Boolean,
    @Json(name = "canonicalUrl")
    val canonicalUrl: String,
    @Json(name = "collaborative")
    val collaborative: Boolean,
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "editable")
    val editable: Boolean,
    @Json(name = "followers")
    val followers: Followers,
    @Json(name = "id")
    val id: String,
    @Json(name = "listItems")
    val listItems: ListItems,
    @Json(name = "name")
    val name: String,
    @Json(name = "photo")
    val photo: Photo,
    @Json(name = "type")
    val type: String,
    @Json(name = "updatedAt")
    val updatedAt: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "user")
    val user: User
)