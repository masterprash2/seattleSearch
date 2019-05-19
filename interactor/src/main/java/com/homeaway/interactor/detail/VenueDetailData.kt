package com.homeaway.interactor.detail

import com.homeaway.entity.detail.Location

data class VenueDetailData(
    val id: String,
    val name: String,
    val webLink: String?,
    val location: Location
) {

    fun toDisplayAddress(): String {
        val address = StringBuilder()
        for (addr in location.formattedAddress) {
            address.append(addr).append(", ")
        }
        if (address.isNotEmpty()) {
            address.deleteCharAt(address.length - 1)
            address.deleteCharAt(address.length - 1)
        }
        return address.toString()
    }
}
