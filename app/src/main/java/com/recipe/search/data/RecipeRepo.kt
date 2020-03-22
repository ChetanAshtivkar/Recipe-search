package com.recipe.search.data

import android.util.Log
import com.recipe.search.data.database.RecipeDao
import com.recipe.search.data.network.RetrofitFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Chetan on 22/03/20.
 */
class RecipeRepo(private val recipeDao: RecipeDao) {

    fun searchRecipes(searchText: String) = CoroutineScope(Dispatchers.IO).launch {
        RetrofitFactory.makeRetrofitService().getRecipes(searchText)
            .enqueue(object : Callback<RecipeResponse> {
                override fun onFailure(call: Call<RecipeResponse>, t: Throwable) {
                    Log.d("RecipeRepo", "RecipeRepo")
                }

                override fun onResponse(
                    call: Call<RecipeResponse>,
                    response: Response<RecipeResponse>
                ) {
                    if (response.isSuccessful) {
                        CoroutineScope(Dispatchers.IO).launch {
                            response.body()?.recipes?.let {
                                it.forEach { recipe ->
                                    recipe.searchText = searchText
                                }
                                recipeDao.insertAll(it)
                            }
                        }
                    }
                }
            })
    }

    fun getRecipesFromLocal(searchText: String) = recipeDao.getSearchResults(searchText)
}