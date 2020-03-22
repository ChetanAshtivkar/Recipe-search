package com.recipe.search.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.recipe.search.data.Recipe

/**
 * Created by Chetan on 22/03/20.
 */
@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe ORDER BY _id DESC")
    fun getAll(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(picture: List<Recipe>)

    @Query("SELECT * FROM recipe WHERE searchText = :searchText ORDER BY _id DESC")
    fun getSearchResults(searchText: String): LiveData<List<Recipe>>
}