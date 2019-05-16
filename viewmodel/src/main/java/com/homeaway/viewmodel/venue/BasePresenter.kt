package com.homeaway.viewmodel.venue

abstract class BasePresenter<T : BaseViewData>(val viewData: T) {

    open fun showLoading() {
        viewData.isErrorLoading.set(false)
        viewData.isLoading.set(true)
    }

}