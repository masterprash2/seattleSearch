package com.homeaway.seattlesearch.app.di

import android.content.Context
import com.homeaway.gateway.FavoriteGateway
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gatewayimpl.FilePersistentFavoriteGateway
import com.homeaway.gatewayimpl.retrofit.FoursquareApi
import com.homeaway.seattlesearch.app.SeattleSearchApp
import com.homeaway.seattlesearch.dev.DevVenuesGateway
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SeattleSearchAppModule {


    @AppScope
    @Provides
    fun context(app: SeattleSearchApp): Context {
        return app
    }


    @BackgroundThreadScheduler
    @AppScope
    @Provides
    fun backgroundThreadScheduler(): Scheduler {
        return Schedulers.computation()
    }

    @MainThreadScheduler
    @Provides
    fun mainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    fun locationGateway(): LocationGateway {
        return object : LocationGateway {
            override fun getCityCenterLat(): Double {
                return 47.6062
            }

            override fun getCityCenterLng(): Double {
                return -122.3321
            }

            override fun calculateDistance(fromLat: Double, fromLong: Double, toLat: Double, toLong: Double): Double {
                return 0.0
            }

        }
    }

    @AppScope
    @Provides
    fun favoriteGateway(filePersistentFavoriteGateway: FilePersistentFavoriteGateway) : FavoriteGateway {
        return filePersistentFavoriteGateway
    }

    @AppScope
    @Provides
    fun venuesGateway(
        context: Context,
        foursquareApi: FoursquareApi, @BackgroundThreadScheduler scheduler: Scheduler
    ): VenuesGateway {
        return DevVenuesGateway(context)
//        return VenuesGatewayImpl(
//            context = context,
//            backgroundThread = scheduler,
//            foursquareApi = foursquareApi
//        )
    }

}