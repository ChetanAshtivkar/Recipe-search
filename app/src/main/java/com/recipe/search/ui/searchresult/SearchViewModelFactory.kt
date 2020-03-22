package com.recipe.search.ui.searchresult

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.recipe.search.data.RecipeRepo

/**
 * Created by Chetan on 22/03/20.
 */
class SearchViewModelFactory(
    private val application: Application,
    private val recipeRepo: RecipeRepo
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(application = application, repo = recipeRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
