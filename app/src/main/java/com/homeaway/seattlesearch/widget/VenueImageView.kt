package com.homeaway.seattlesearch.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import com.squareup.picasso.Picasso

class VenueImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {

    init {
        setColorFilter(Color.DKGRAY);
    }

    fun setImageUrl(imageUrl: String?) {
        val into = Picasso.get()
        into.cancelRequest(this)
        setImageBitmap(null)
        if(!imageUrl.isNullOrEmpty())
            into.load(imageUrl).into(this)
    }

}