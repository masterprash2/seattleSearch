package com.homeaway.seattlesearch.activity.detail

import android.os.Bundle
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}