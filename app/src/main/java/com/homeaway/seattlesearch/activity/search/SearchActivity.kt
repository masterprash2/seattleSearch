package com.homeaway.seattlesearch.activity.search

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.homeaway.seattlesearch.R
import com.homeaway.seattlesearch.databinding.ActivitySearchBinding
import com.homeaway.viewmodel.venue.search.VenueSearchViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewBinding: ActivitySearchBinding
    @Inject
    lateinit var viewModel: VenueSearchViewModel

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        compositeDisposable = CompositeDisposable()
        viewBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupViewBinding()
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupViewBinding() {
        viewBinding.model = viewModel
        viewBinding.contentMain.model = viewModel
        viewBinding.contentMain.data = viewModel.getViewData()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.contentMain.results
        val searchResultsAdapter = SearchResultsAdapter(layoutInflater)
        recyclerView.adapter = searchResultsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        compositeDisposable.add(viewModel.getViewData().observeResults()
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                searchResultsAdapter.updateWithNewList(it)
            })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        setupSearchView(searchView)
        return true
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setIconifiedByDefault(false)
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchText(newText ?: "")
                return true
            }
        })
        compositeDisposable.add(viewModel.getViewData().observeSearchText()
            .filter { !it.equals(searchView.query.toString()) }
            .subscribe { searchView.setQuery(it, false) })
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }


}