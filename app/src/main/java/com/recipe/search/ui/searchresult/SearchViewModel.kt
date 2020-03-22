package com.recipe.search.ui.searchresult

import android.app.Application
import com.recipe.search.common.BaseViewModel
import com.recipe.search.data.RecipeRepo

/**
 * Created by Chetan on 22/03/20.
 */
class SearchViewModel(application: Application, private val repo: RecipeRepo) :
    BaseViewModel(application = application) {
    fun setSearchText(searchText: String?) {
        searchText?.let {
            repo.searchRecipes(it)
        }
    }
}