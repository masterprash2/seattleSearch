package com.homeaway.gateway

interface MapsGateway {

    fun getMapsImageFromCenter(lat: Double, lng: Double): String

}