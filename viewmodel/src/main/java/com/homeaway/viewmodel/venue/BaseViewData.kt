package com.homeaway.viewmodel.venue

import androidx.databinding.ObservableBoolean

abstract class BaseViewData {

    val isLoading = ObservableBoolean()
    val isErrorLoading = ObservableBoolean()
    val isContentAvailable = ObservableBoolean()

}