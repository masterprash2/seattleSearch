package com.homeaway.seattlesearch.activity.di

import com.homeaway.seattlesearch.activity.detail.DetailActivity
import com.homeaway.seattlesearch.activity.detail.di.DetailActivityModule
import com.homeaway.seattlesearch.activity.search.SearchActivity
import com.homeaway.seattlesearch.activity.search.di.SearchActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    internal abstract fun searchActivity(): SearchActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    internal abstract fun detailActivity(): DetailActivity

}