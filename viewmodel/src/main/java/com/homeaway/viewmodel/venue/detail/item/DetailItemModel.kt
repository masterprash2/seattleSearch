package com.homeaway.viewmodel.venue.detail.item


enum class Type {
    KEY_VALUE,
    WEB_LINK;

    companion object {
        private val values = Type.values()
        fun from(ordinal: Int): Type {
            return values[ordinal]
        }
    }


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