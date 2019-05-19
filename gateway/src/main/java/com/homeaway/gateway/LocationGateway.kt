package com.homeaway.gateway

interface LocationGateway {

    fun calculateDistanceFromCenter(toLat: Double, toLong: Double): Double
    fun getCityCenterLat() : Double
    fun getCityCenterLng() : Double

}