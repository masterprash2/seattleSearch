package com.homeaway.viewmodel.venue.detail

import androidx.databinding.ObservableField
import com.homeaway.viewmodel.venue.BaseViewData
import com.homeaway.viewmodel.venue.detail.item.DetailItemModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class DetailViewData @Inject constructor() : BaseViewData() {

    val venueMapImage = ObservableField<String>()
    private val venueDetails = BehaviorSubject.create<List<DetailItemModel>>()

    internal fun setVenueDetails(value: List<DetailItemModel>) {
        venueDetails.onNext(value)
    }

    fun getVenueDetails(): List<DetailItemModel> {
        if (venueDetails.hasValue()) {
            return venueDetails.value!!
        } else {
            return Arrays.asList()
        }
    }

    fun observeVenueDetails(): Observable<List<DetailItemModel>> {
        return venueDetails;
    }

}