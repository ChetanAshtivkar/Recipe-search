package com.recipe.search.ui.searchresult

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.recipe.search.R
import com.recipe.search.common.BUNDLE_SEARCH
import com.recipe.search.common.BaseActivity
import com.recipe.search.data.Recipe
import com.recipe.search.data.RecipeRepo
import com.recipe.search.data.database.RecipeDatabase
import com.recipe.search.databinding.ActivitySearchResultBinding
import com.recipe.search.ui.searchresult.adapter.RecipeAdapter
import com.recipe.search.ui.searchresult.adapter.RecipeListener

class SearchResultActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: RecipeAdapter

    override fun setupLiveDataComponents() {
        viewModel.searchResult.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)

        val recipeDao = RecipeDatabase.getInstance(application)!!.recipeDao()

        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(
                application,
                RecipeRepo(recipeDao)
            )
        ).get(SearchViewModel::class.java)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setTitle(getString(R.string.title_search_result))

        setupRecyclerViewAdapter()
        viewModel.setSearchText(intent.extras?.getString(BUNDLE_SEARCH))

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun setupRecyclerViewAdapter() {
        adapter = RecipeAdapter(RecipeListener { recipe: Recipe, i: Int ->
            //TODO : Click listener
        })

        binding.resultList.adapter = adapter

//        val swipeHandler = object : SwipeToDeleteCallback(this) {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val adapter = binding.resultList.adapter as RecipeAdapter
//                adapter.currentList.removeAt(viewHolder.adapterPosition)
//            }
//
//        }
//        val itemTouchHelper = ItemTouchHelper(swipeHandler)
//        itemTouchHelper.attachToRecyclerView(binding.resultList)
    }
}
