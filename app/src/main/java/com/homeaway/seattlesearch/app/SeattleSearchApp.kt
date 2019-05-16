package com.homeaway.seattlesearch.app

import com.homeaway.seattlesearch.app.di.DaggerSeattleSearchAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SeattleSearchApp : DaggerApplication() {

    lateinit var androidInjector: AndroidInjector<SeattleSearchApp>

    override fun applicationInjector(): AndroidInjector<out SeattleSearchApp> {
        return androidInjector
    }

    override fun onCreate() {
        androidInjector = DaggerSeattleSearchAppComponent.factory().create(this)
        super.onCreate()

    }

}