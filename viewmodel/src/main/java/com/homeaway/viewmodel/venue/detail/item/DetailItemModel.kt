package com.homeaway.viewmodel.venue.detail.item


enum class Type {
    KEY_VALUE,
    WEB_LINK
}

class DetailItemModel(
    val key: String, val value: String, val type: Type,
    private val detailItemNavigation: DetailItemNavigation
) {


    fun performClick() {
        when (type) {
            Type.KEY_VALUE -> return
            Type.WEB_LINK -> detailItemNavigation.openWebLink(value)
        }
    }


}