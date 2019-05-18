package com.homeaway.interactor.detail

import com.homeaway.entity.detail.Contact
import com.homeaway.entity.detail.Location

data class VenueDetailData(
    val id: String,
    val mapImageUrl: String,
    val name: String,
    val webLink: String?,
    val location: Location,
    val contact: Contact,
    val description: String
) {

    fun toDisplayAddress(): String {
        val address = StringBuilder()
        for (addr in location.address) {
            address.append(addr).append(", ")
        }
        if (address.isNotEmpty()) {
            address.trimToSize()
            address.deleteCharAt(address.length - 1)
        }
        return address.toString()
    }
}
