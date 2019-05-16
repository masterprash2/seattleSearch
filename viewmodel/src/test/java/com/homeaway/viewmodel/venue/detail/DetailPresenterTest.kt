package com.homeaway.viewmodel.venue.detail

import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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
        assertTrue(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @Test
    fun handleErrorLoading() {
        presenter.showError()
        assertFalse(data.isLoading.get())
        assertTrue(data.isErrorLoading.get())
    }

    @Test
    fun handleSuccess() {
        presenter.handleSuccess()
        assertFalse(data.isLoading.get())
        assertFalse(data.isErrorLoading.get())
    }

    @After
    fun tearDown() {
    }
}