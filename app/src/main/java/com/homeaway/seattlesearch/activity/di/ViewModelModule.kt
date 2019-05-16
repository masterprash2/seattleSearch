package com.homeaway.seattlesearch.activity.di

import androidx.lifecycle.ViewModelProvider
import com.homeaway.viewmodel.venue.ViewModelFactory
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import com.homeaway.viewmodel.venue.detail.DetailViewModelFactory
import com.homeaway.viewmodel.venue.search.VenueSearchViewModel
import com.homeaway.viewmodel.venue.search.VenueSearchViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(VenueSearchViewModel::class)
    fun searchViewModelFactory(factory: VenueSearchViewModelFactory): ViewModelFactory {
        return factory
    }

    @Provides
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun detailViewModelFactory(factory: DetailViewModelFactory): ViewModelFactory {
        return factory
    }

    @ActivityScope
    @Provides
    fun searchViewModel(viewModelProvider: ViewModelProvider): VenueSearchViewModel {
        return viewModelProvider.get(VenueSearchViewModel::class.java)
    }


}