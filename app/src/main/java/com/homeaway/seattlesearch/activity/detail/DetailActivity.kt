package com.homeaway.seattlesearch.activity.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.homeaway.gateway.LocationGateway
import com.homeaway.seattlesearch.R
import com.homeaway.seattlesearch.databinding.ActivityDetailBinding
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DetailActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewBinding: ActivityDetailBinding

    @Inject
    lateinit var locationGateway: LocationGateway

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.map.onCreate(savedInstanceState)
        setupRecyclerView()
        viewBinding.contentDetail.data = viewModel.viewData()
        compositeDisposable.add(viewModel.viewData().observeVenueLocation()
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                updateLocation(it.lat, it.lng)
            })
        observeFavoriteUpdates()
        setSupportActionBar(viewBinding.toolbar)
    }

    private fun observeFavoriteUpdates() {
        compositeDisposable.add(viewModel.viewData().observeFavoriteUpdates().subscribe {
            invalidateOptionsMenu()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        val favorite = menu.findItem(R.id.favorite)
        favorite.setIcon(if (viewModel.viewData().isFavorite()) R.drawable.favorite_yes else R.drawable.favorite_no)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorite) {
            viewModel.toggleFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        viewBinding.map.onStart()
        viewModel.loadDetails(intent.getStringExtra(BUNDLE_KEY_VENUE_ID))
    }


    override fun onResume() {
        super.onResume()
        viewBinding.map.onResume()
    }

    override fun onPause() {
        viewBinding.map.onPause()
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        viewBinding.map.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        viewBinding.map.onStop()
        super.onStop()
    }


    override fun onDestroy() {
        viewBinding.map.onDestroy()
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.contentDetail.details;
        val detailsAdapter = DetailsAdapter(layoutInflater)
        recyclerView.apply {
            adapter = detailsAdapter
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.VERTICAL, false)
        }
        compositeDisposable.add(viewModel.viewData().observeVenueDetails()
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                detailsAdapter.updateWithNewList(it)
            })
    }


    private fun updateLocation(lat: Double, lng: Double) {
        viewBinding.map.getMapAsync {
            val centerOfCity = MarkerOptions()
            centerOfCity.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            centerOfCity.position(LatLng(locationGateway.getCityCenterLat(), locationGateway.getCityCenterLng()))
            it.addMarker(centerOfCity)

            val venueMarker = MarkerOptions()
            venueMarker.position(LatLng(lat, lng))
            centerOfCity.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            it.addMarker(venueMarker)
            it.moveCamera(
                CameraUpdateFactory.newLatLngBounds(
                    LatLngBounds.builder()
                        .include(venueMarker.position)
                        .include(centerOfCity.position)
                        .build()
                    , 100
                )
            )
        }
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