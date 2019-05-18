package com.homeaway.seattlesearch.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.squareup.picasso.Picasso

class VenueImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    fun setImageUrl(imageUrl: String) {
        Picasso.get().load(imageUrl).into(this)
    }

    fun setVenueId(venueId : String) {

    }

}