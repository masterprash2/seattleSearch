package com.homeaway.seattlesearch.activity.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.databinding.ActivityDetailBinding
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewBinding: ActivityDetailBinding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setupRecyclerView()
        viewBinding.contentDetail.data = viewModel.viewData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadDetails(intent.getStringExtra(BUNDLE_KEY_VENUE_ID))
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.contentDetail.details;
        val detailsAdapter = DetailsAdapter(layoutInflater)
        recyclerView.apply {
            adapter = detailsAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.VERTICAL, false)
        }
        compositeDisposable.add(viewModel.viewData().observeVenueDetails().subscribe {
            detailsAdapter.updateWithNewList(it)
        })
    }


    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    companion object {

        val BUNDLE_KEY_VENUE_ID = "venueId"

        fun launch(activity: Activity, venueId: String) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(BUNDLE_KEY_VENUE_ID, venueId)
            activity.startActivity(intent)
        }
    }
}