package com.homeaway.seattlesearch.activity.di

import com.homeaway.seattlesearch.activity.search.SearchActivity
import com.homeaway.seattlesearch.activity.search.di.SearchActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchActivityModule::class, ViewModelModule::class])
    internal abstract fun searchActivity(): SearchActivity


}