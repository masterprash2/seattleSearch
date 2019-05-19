package com.homeaway.gatewayimpl

import android.location.Location
import com.homeaway.gateway.LocationGateway
import javax.inject.Inject

class LocationGatewayImpl @Inject constructor() : LocationGateway {
    override fun getCityCenterLat(): Double {
        return 47.6062
    }

    override fun getCityCenterLng(): Double {
        return -122.3321
    }

    override fun calculateDistance(fromLat: Double, fromLong: Double, toLat: Double, toLong: Double): Double {
        val from = Location("From")
        from.latitude = fromLat
        from.longitude = fromLong
        val to = Location("TO")
        to.latitude = toLat
        to.longitude = toLong
        return from.distanceTo(to).toDouble()
    }
}