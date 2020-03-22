package com.recipe.search.data

/**
 * Created by Chetan on 22/03/20.
 */
data class RecipeResponse(
    val count: Int,
    val recipes: List<Recipe>
)