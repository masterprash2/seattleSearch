package com.homeaway.gatewayimpl.retrofit.di

import android.content.Context
import com.homeaway.gatewayimpl.R
import com.homeaway.gatewayimpl.retrofit.FoursquareApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
class RetrofitModule {

    @Provides
    @RetrofitScope
    fun okhttp(): OkHttpClient {
        val connectionTimeoutInterceptor = ConnectionTimeoutInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(connectionTimeoutInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        connectionTimeoutInterceptor.attach(client)
        return client

    }

//    client.dispatcher().cancelAll();
//    client.connectionPool().evictAll();

    @Provides
    @RetrofitScope
    fun retrofit(context: Context, okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().build()
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.foursquareApiBase))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @RetrofitScope
    fun foursquareApi(retrofit: Retrofit): FoursquareApi {
        return retrofit.create(FoursquareApi::class.java)
    }

}