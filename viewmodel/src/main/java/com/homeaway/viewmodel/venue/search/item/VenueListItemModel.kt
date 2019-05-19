package com.homeaway.viewmodel.venue.search.item

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.homeaway.gateway.FavoriteGateway
import com.homeaway.interactor.search.VenueListItemData
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference

class VenueListItemModel(
    private val data: VenueListItemData,
    private val itemNavigation: ItemNavigation,
    private val favoriteGateway: FavoriteGateway
) {

    val isFavorite : ObservableBoolean

    init {
        isFavorite = ObservableBoolean()
        val weakRef = WeakReference<VenueListItemModel>(this)
        observeFavorites(weakRef = weakRef, favoriteGateway = favoriteGateway, venueId = data.id)
    }

    val name = ObservableField<String>(data.name)
    val category = ObservableField<String>(data.category)
    val distance = ObservableField<String>(data.distance)
    val photoUrl = ObservableField<String>(data.photoUrl)

    fun handleOnClick() {
        itemNavigation.openDetail(data.id)
    }

    companion object {

        private fun observeFavorites(
            favoriteGateway: FavoriteGateway,
            venueId: String,
            weakRef: WeakReference<VenueListItemModel>
        ) {
            favoriteGateway.venueFavoriteUpdates(venueId).subscribe(object : DisposableObserver<Boolean>() {
                override fun onComplete() {}

                override fun onNext(value: Boolean) {
                    val model = weakRef.get()
                    if (model != null)
                        model.isFavorite.set(value)
                    else {
                        dispose()
                    }
                }

                override fun onError(e: Throwable) {}
            })
        }

    }

}