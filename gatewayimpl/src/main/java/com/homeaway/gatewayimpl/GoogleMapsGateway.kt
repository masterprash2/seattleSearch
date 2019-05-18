package com.homeaway.gatewayimpl

import android.net.Uri
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.MapsGateway
import javax.inject.Inject

class GoogleMapsGateway @Inject constructor(private val locationGateway: LocationGateway) : MapsGateway {

    private val apiKey = "AIzaSyA8bPE0Zz-6p2Al7mk80piPHc2v7QdU7hk"

    override fun getMapsImageFromCenter(lat: Double, lng: Double): String {
        if(true) {
            return "https://maps.googleapis.com/maps/api/staticmap?markers=color%3Ablue%7C47.6062%2C122.3321&markers=color%3Ared%7C47.60621%2C-122.33207&size=640x300&maptype=roadmap&format=JPEG&key="
        }

        val cityCenterLat = locationGateway.getCityCenterLat()
        val cityCenterLng = locationGateway.getCityCenterLng();
        val builder = Uri.parse("https://maps.googleapis.com/maps/api/staticmap?")
            .buildUpon()
        return builder
            .appendQueryParameter("markers", "color:blue|$cityCenterLat,$cityCenterLng")
            .appendQueryParameter("markers", "color:red|$lat,$lng")
            .appendQueryParameter("size", "500x300")
            .appendQueryParameter("maptype", "roadmap")
            .appendQueryParameter("format", "JPEG")
            .appendQueryParameter("key", apiKey)
            .toString()
    }

}