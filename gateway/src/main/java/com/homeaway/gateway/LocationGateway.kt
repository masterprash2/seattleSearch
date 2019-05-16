package com.homeaway.gateway

interface LocationGateway {

    fun calculateDistance(fromLat: Double, fromLong: Double, toLat: Double, toLong: Double): Double

}