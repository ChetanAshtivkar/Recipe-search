package com.recipe.search.ui.searchresult

import android.app.Application
import androidx.lifecycle.LiveData
import com.recipe.search.common.BaseViewModel
import com.recipe.search.data.Recipe
import com.recipe.search.data.RecipeRepo

/**
 * Created by Chetan on 22/03/20.
 */
class SearchViewModel(application: Application, private val repo: RecipeRepo) :
    BaseViewModel(application = application) {

    public lateinit var searchResult: LiveData<List<Recipe>>


    fun setSearchText(searchText: String?) {
        searchText?.let {
            repo.searchRecipes(it)
            searchResult = repo.getRecipesFromLocal(searchText)
        }
    }
}