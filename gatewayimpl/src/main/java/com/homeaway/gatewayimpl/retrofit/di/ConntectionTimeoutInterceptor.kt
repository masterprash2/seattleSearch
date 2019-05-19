package com.homeaway.gatewayimpl.retrofit.di

import io.reactivex.subjects.PublishSubject
import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketTimeoutException

class ConntectionTimeoutInterceptor : Interceptor {

    val resetSingnal = PublishSubject.create<Boolean>()

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (e: Exception) {
            if (e is SocketTimeoutException && e.message!!.contains("timeout")) {
                chain.call().cancel()
                resetOkhttpClient()
            }
            throw e
        }
    }

    private fun resetOkhttpClient() {
        resetSingnal.onNext(true)
    }

}