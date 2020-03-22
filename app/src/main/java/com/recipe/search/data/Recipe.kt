package com.recipe.search.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Chetan on 22/03/20.
 */
@Entity(tableName = "recipe")

data class Recipe(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val title: String,
    val publisher: String,

    var searchText: String? = null,

    @SerializedName("image_url")
    val imageUrl: String,

    @SerializedName("social_rank")
    val socialRank: Double,

    @SerializedName("source_url")
    val sourceUrl: String,

    @SerializedName("recipe_id")
    val recipeId: String,

    @SerializedName("publisher_url")
    val publisherUrl: String
)
