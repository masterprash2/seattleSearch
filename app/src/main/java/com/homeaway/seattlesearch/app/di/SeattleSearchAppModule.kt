package com.homeaway.seattlesearch.app.di

import android.content.Context
import com.homeaway.entity.search.SearchResults
import com.homeaway.gateway.LocationGateway
import com.homeaway.gateway.VenuesGateway
import com.homeaway.gateway.data.Response
import com.homeaway.seattlesearch.app.SeattleSearchApp
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
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
    fun venuesGateway() : VenuesGateway {
        return object : VenuesGateway {
            override fun getSearchResults(query: String): Observable<Response<SearchResults>> {
                return Observable.never()
            }

        }
    }

    @Provides
    fun locationGateway() : LocationGateway {
        return object : LocationGateway {
            override fun calculateDistance(fromLat: Double, fromLong: Double, toLat: Double, toLong: Double): Double {
                return 0.0
            }

        }
    }


}