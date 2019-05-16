package com.homeaway.viewmodel.venue.detail

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import com.homeaway.viewmodel.venue.BaseViewModel
import com.homeaway.viewmodel.venue.ViewModelFactory

@AutoFactory(implementing = [ViewModelFactory::class])
class DetailViewModel(@Provided private val presenter: DetailPresenter) : BaseViewModel() {

}