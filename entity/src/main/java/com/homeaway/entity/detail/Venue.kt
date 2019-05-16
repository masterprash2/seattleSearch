package com.homeaway.entity.detail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "attributes")
    val attributes: Attributes,
    @Json(name = "beenHere")
    val beenHere: BeenHere,
    @Json(name = "bestPhoto")
    val bestPhoto: BestPhoto,
    @Json(name = "canonicalUrl")
    val canonicalUrl: String,
    @Json(name = "categories")
    val categories: List<Category>,
    @Json(name = "contact")
    val contact: Contact,
    @Json(name = "createdAt")
    val createdAt: Int,
    @Json(name = "description")
    val description: String,
    @Json(name = "hereNow")
    val hereNow: HereNow,
    @Json(name = "hours")
    val hours: Hours,
    @Json(name = "id")
    val id: String,
    @Json(name = "inbox")
    val inbox: Inbox,
    @Json(name = "likes")
    val likes: Likes,
    @Json(name = "listed")
    val listed: Listed,
    @Json(name = "location")
    val location: Location,
    @Json(name = "name")
    val name: String,
    @Json(name = "page")
    val page: Page,
    @Json(name = "pageUpdates")
    val pageUpdates: PageUpdates,
    @Json(name = "photos")
    val photos: Photos,
    @Json(name = "phrases")
    val phrases: List<Phrase>,
    @Json(name = "popular")
    val popular: Popular,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "ratingColor")
    val ratingColor: String,
    @Json(name = "ratingSignals")
    val ratingSignals: Int,
    @Json(name = "shortUrl")
    val shortUrl: String,
    @Json(name = "stats")
    val stats: Stats,
    @Json(name = "storeId")
    val storeId: String,
    @Json(name = "timeZone")
    val timeZone: String,
    @Json(name = "tips")
    val tips: Tips,
    @Json(name = "url")
    val url: String,
    @Json(name = "venueChains")
    val venueChains: List<Any>,
    @Json(name = "verified")
    val verified: Boolean
)