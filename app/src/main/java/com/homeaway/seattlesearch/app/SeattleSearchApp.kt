package com.homeaway.seattlesearch.app

import android.content.Context
import com.homeaway.seattlesearch.app.di.DaggerSeattleSearchAppComponent
import com.homeaway.seattlesearch.app.di.SeattleSearchAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SeattleSearchApp : DaggerApplication() {

    lateinit var androidInjector: SeattleSearchAppComponent

    override fun applicationInjector(): SeattleSearchAppComponent {
        return androidInjector
    }

    override fun onCreate() {
        androidInjector = DaggerSeattleSearchAppComponent.factory().create(this)
        super.onCreate()
    }

    companion object {
        fun getComponent(context: Context): SeattleSearchAppComponent {
            return (context.applicationContext as SeattleSearchApp).androidInjector
        }
    }

}