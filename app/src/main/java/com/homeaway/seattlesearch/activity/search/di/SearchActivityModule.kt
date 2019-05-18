package com.homeaway.seattlesearch.activity.search.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.homeaway.seattlesearch.activity.di.ActivityScope
import com.homeaway.seattlesearch.activity.di.ViewModelKey
import com.homeaway.seattlesearch.activity.di.ViewModelProviderFactory
import com.homeaway.seattlesearch.activity.search.SearchActivity
import com.homeaway.seattlesearch.activity.search.SearchNavigationImpl
import com.homeaway.seattlesearch.databinding.ActivitySearchBinding
import com.homeaway.viewmodel.venue.ViewModelFactory
import com.homeaway.viewmodel.venue.search.VenueSearchNavigation
import com.homeaway.viewmodel.venue.search.VenueSearchViewModel
import com.homeaway.viewmodel.venue.search.VenueSearchViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class SearchActivityModule {

    @Provides
    fun activityBidning(activity: SearchActivity): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(activity.layoutInflater)
    }

    @ActivityScope
    @Provides
    fun viewModelProvider(activity: SearchActivity, providerFactory: ViewModelProviderFactory): ViewModelProvider {
        return ViewModelProviders.of(activity, providerFactory)
    }

    @ActivityScope
    @Provides
    fun venueSearchNavigation(searchNavigationImpl: SearchNavigationImpl): VenueSearchNavigation {
        return searchNavigationImpl
    }

    @Provides
    @IntoMap
    @ViewModelKey(VenueSearchViewModel::class)
    fun searchViewModelFactory(factory: VenueSearchViewModelFactory): ViewModelFactory {
        return factory
    }


    @ActivityScope
    @Provides
    fun searchViewModel(viewModelProvider: ViewModelProvider): VenueSearchViewModel {
        return viewModelProvider.get(VenueSearchViewModel::class.java)
    }



}