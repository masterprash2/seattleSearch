package com.homeaway.viewmodel.venue

import androidx.lifecycle.ViewModel

interface ViewModelFactory {

    public fun <T : ViewModel> create(): T

}