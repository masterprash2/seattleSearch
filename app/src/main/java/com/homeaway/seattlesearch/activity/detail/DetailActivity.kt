package com.homeaway.seattlesearch.activity.detail

import android.os.Bundle
import com.homeaway.seattlesearch.databinding.ActivityDetailBinding
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewBinding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.contentDetail.details;

        recyclerView.apply {
        }
    }
}