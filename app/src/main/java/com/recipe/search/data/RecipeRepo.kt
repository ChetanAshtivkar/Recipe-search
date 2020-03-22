package com.recipe.search.data

import android.util.Log
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
class RecipeRepo {

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
                        Log.d("RecipeRepo", "RecipeRepo")

                    } else {

                    }
                }
            })
    }
}