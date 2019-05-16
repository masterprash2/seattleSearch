package com.homeaway.seattlesearch.activity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.homeaway.viewmodel.venue.ViewModelFactory
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(private val map: Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModelFactory>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return (map[modelClass] ?: error("")).create()
    }

}