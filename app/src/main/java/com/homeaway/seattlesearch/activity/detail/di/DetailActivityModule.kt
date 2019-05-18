package com.homeaway.seattlesearch.activity.detail.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.homeaway.seattlesearch.activity.detail.DetailActivity
import com.homeaway.seattlesearch.activity.detail.DetailViewNavigationImpl
import com.homeaway.seattlesearch.activity.di.ActivityScope
import com.homeaway.seattlesearch.activity.di.ViewModelKey
import com.homeaway.seattlesearch.activity.di.ViewModelProviderFactory
import com.homeaway.seattlesearch.databinding.ActivitySearchBinding
import com.homeaway.viewmodel.venue.ViewModelFactory
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import com.homeaway.viewmodel.venue.detail.DetailViewModelFactory
import com.homeaway.viewmodel.venue.detail.DetailViewNavigation
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class DetailActivityModule {
//
//    @Provides
//    fun activityBidning(activity: DetailActivity): ActivitySearchBinding {
//        return ActivitySearchBinding.inflate(activity.layoutInflater)
//    }

    @ActivityScope
    @Provides
    fun viewModelProvider(activity: DetailActivity, providerFactory: ViewModelProviderFactory): ViewModelProvider {
        return ViewModelProviders.of(activity, providerFactory)
    }

    @ActivityScope
    @Provides
    fun detailViewNavigation(navigationImpl: DetailViewNavigationImpl): DetailViewNavigation {
        return navigationImpl
    }



    @Provides
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun detailViewModelFactory(factory: DetailViewModelFactory): ViewModelFactory {
        return factory
    }

    @ActivityScope
    @Provides
    fun detailViewModel(viewModelProvider: ViewModelProvider): DetailViewModel {
        return viewModelProvider.get(DetailViewModel::class.java)
    }
}