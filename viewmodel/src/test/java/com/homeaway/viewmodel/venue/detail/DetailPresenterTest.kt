package com.homeaway.viewmodel.venue.detail

import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailPresenterTest {

    lateinit var presenter: DetailPresenter
    lateinit var data: DetailViewData

    @Before
    fun setUp() {
        data = DetailViewData()
        presenter = DetailPresenter(data)
    }

    @Test
    fun handleLoading() {
        presenter.showLoading()
    }

    @After
    fun tearDown() {
    }
}