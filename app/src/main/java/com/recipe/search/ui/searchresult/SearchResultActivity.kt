package com.recipe.search.ui.searchresult

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.recipe.search.R
import com.recipe.search.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)

        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(application)
        ).get(SearchViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
