package com.recipe.search.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Chetan on 22/03/20.
 */
data class Recipe(
    val _id: String,
    val title: String,
    val publisher: String,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("social_rank")
    val socialRank: Int,

    @SerializedName("source_url")
    val sourceUrl: String,

    @SerializedName("recipe_id")
    val recipeId: String,

    @SerializedName("publisher_url")
    val publisherUrl: String
)
