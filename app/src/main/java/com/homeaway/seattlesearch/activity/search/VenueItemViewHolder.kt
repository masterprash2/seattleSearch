package com.homeaway.seattlesearch.activity.search

import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.databinding.ItemVenueBinding
import com.homeaway.viewmodel.venue.search.item.VenueListItemModel

class VenueItemViewHolder(private val itemBinding: ItemVenueBinding) : RecyclerView.ViewHolder(itemBinding.root) {

//    var viewModel: VenueListItemModel? = null

//    val favoriteListener = object : Observable.OnPropertyChangedCallback() {
//        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//            updateFavoriteColor(itemBinding.favorite, viewModel!!.isFavorite.get())
//        }
//    }

    fun bind(viewModel: VenueListItemModel) {
//        unBindOldViewModel()
//        this.viewModel = viewModel
        itemBinding.model = viewModel
//        viewModel.isFavorite.addOnPropertyChangedCallback(favoriteListener)
//        updateFavoriteColor(itemBinding.favorite, viewModel.isFavorite.get())
    }

//    private fun unBindOldViewModel() {
//        val lastViewModel = this.viewModel
//        lastViewModel?.isFavorite?.removeOnPropertyChangedCallback(favoriteListener)
//        this.viewModel = null
//    }
//
//    private fun updateFavoriteColor(favorite: ImageView, value: Boolean) {
//        favorite.setImageResource(if (value) R.drawable.favorite_yes else R.drawable.favorite_no)
//    }

}