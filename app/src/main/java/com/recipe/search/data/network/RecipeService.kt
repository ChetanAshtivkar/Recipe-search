package com.recipe.search.data.network

import com.recipe.search.data.RecipeResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Chetan on 22/03/20.
 */
interface RecipeService {
    @GET("api/search")
    fun getRecipes(
        @Query("q") searchText: String
    ): Call<RecipeResponse>
}

object RetrofitFactory {
    const val BASE_URL = "https://recipesapi.herokuapp.com/"

    fun makeRetrofitService(): RecipeService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(RecipeService::class.java)
    }
}
