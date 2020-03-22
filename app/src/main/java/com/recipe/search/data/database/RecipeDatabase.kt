package com.recipe.search.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.recipe.search.data.Recipe

/**
 * Created by Chetan on 22/03/20.
 */
private const val DATABASE_NAME = "recipe_db"

@Database(
    entities = [Recipe::class],
    version = 3,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase? {
            if (INSTANCE == null) {
                synchronized(RecipeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RecipeDatabase::class.java,
                            DATABASE_NAME
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}