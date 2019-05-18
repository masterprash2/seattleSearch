package com.homeaway.seattlesearch.activity.detail

import android.content.Intent
import com.homeaway.viewmodel.venue.detail.DetailViewNavigation
import javax.inject.Inject

class DetailViewNavigationImpl @Inject constructor(private val activity: DetailActivity) : DetailViewNavigation {
    override fun openWebLink(url: String) {
        val webIntent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
        webIntent.action = Intent.ACTION_VIEW
        activity.startActivity(webIntent)
    }

}