package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemXX(
    @Json(name = "agreeCount")
    val agreeCount: Int,
    @Json(name = "authorInteractionType")
    val authorInteractionType: String,
    @Json(name = "canonicalUrl")
    val canonicalUrl: String,
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "disagreeCount")
    val disagreeCount: Int,
    @Json(name = "editedAt")
    val editedAt: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "lang")
    val lang: String,
    @Json(name = "likes")
    val likes: Likes,
    @Json(name = "logView")
    val logView: Boolean,
    @Json(name = "photo")
    val photo: PhotoXXXX,
    @Json(name = "photourl")
    val photourl: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "todo")
    val todo: Todo,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "user")
    val user: UserXX
)