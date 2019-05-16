package com.homeaway.gateway.data

data class Response<T>(val success: Boolean, val response: T?, val exception: Exception? = null)