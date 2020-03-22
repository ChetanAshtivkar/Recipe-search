package com.recipe.search.ui.searchresult

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Chetan on 22/03/20.
 */
class SearchViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
