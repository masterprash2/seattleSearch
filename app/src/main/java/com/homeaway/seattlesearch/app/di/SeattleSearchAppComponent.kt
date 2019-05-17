package com.homeaway.seattlesearch.app.di

import com.homeaway.gatewayimpl.retrofit.di.RetrofitModule
import com.homeaway.gatewayimpl.retrofit.di.RetrofitScope
import com.homeaway.seattlesearch.activity.di.ActivityBuilderModule
import com.homeaway.seattlesearch.app.SeattleSearchApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@RetrofitScope
@AppScope
@dagger.Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        SeattleSearchAppModule::class,
        RetrofitModule::class]
)
interface SeattleSearchAppComponent : AndroidInjector<SeattleSearchApp> {

    @Component.Factory
    interface Builder : AndroidInjector.Factory<SeattleSearchApp> {
    }
}
