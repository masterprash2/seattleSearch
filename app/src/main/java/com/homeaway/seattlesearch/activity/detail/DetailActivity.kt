package com.homeaway.seattlesearch.activity.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.homeaway.seattlesearch.databinding.ActivityDetailBinding
import com.homeaway.viewmodel.venue.detail.DetailViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DetailActivity : DaggerAppCompatActivity(), OnMapReadyCallback {


    @Inject
    lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var viewBinding: ActivityDetailBinding

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.map.onCreate(savedInstanceState)
        setupRecyclerView()
        viewBinding.contentDetail.data = viewModel.viewData()
        compositeDisposable.add(viewModel.viewData().observeVenueLocation().subscribe {
            updateLocation(it.lat, it.lng)
        })

    }

    private fun updateLocation(lat: Double, lng: Double) {
        viewBinding.map.getMapAsync {
            val centerOfCity = MarkerOptions()
            centerOfCity.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            centerOfCity.position(LatLng(47.6062, -122.3321))
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
                    , 0
                )
            )
        }
    }

    override fun onStart() {
        super.onStart()
        viewBinding.map.onStart()
        viewModel.loadDetails(intent.getStringExtra(BUNDLE_KEY_VENUE_ID))
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

    override fun onMapReady(p0: GoogleMap?) {
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