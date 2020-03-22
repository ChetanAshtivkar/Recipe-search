package com.recipe.search.ui.mainscreen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.recipe.search.R
import com.recipe.search.common.BUNDLE_SEARCH
import com.recipe.search.common.BaseActivity
import com.recipe.search.common.Navigator
import com.recipe.search.databinding.ActivityMainBinding
import com.recipe.search.ui.searchresult.SearchResultActivity

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var searchText: String? = null

    override fun setupLiveDataComponents() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.searchRecipe.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                submitQuery(query)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchText = newText
                return false
            }
        })

        binding.buttonSearch.setOnClickListener {
            submitQuery(searchText)
        }
    }

    private fun submitQuery(query: String?) {
        if (query != null && query.trim().isNotEmpty()) {
            val bundle = Bundle()
            bundle.putString(BUNDLE_SEARCH, query)
            resolveNavigation(
                Navigator(
                    Navigator.NavigationAction.StartActivity,
                    SearchResultActivity::class.java,
                    bundle
                )
            )
        } else {
            Toast.makeText(this, getString(R.string.alert_no_search_text), Toast.LENGTH_SHORT)
                .show()
        }
    }
}