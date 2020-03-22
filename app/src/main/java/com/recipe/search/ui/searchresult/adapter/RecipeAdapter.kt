package com.recipe.search.ui.searchresult.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.recipe.search.data.Recipe
import com.recipe.search.databinding.ListItemRecipeBinding

/**
 * Created by Chetan on 22/03/20.
 */
class RecipeAdapter(val listener: RecipeListener) :
    ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(
        RecipeDiffCallback()
    ) {

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(
            parent
        )
    }

    class RecipeViewHolder private constructor(val binding: ListItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Recipe?, listener: RecipeListener, position: Int) {
            binding.recipe = item
            binding.listener = listener
            binding.position = position
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemRecipeBinding.inflate(layoutInflater, parent, false)
                return RecipeViewHolder(
                    binding
                )
            }
        }
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }
}

open class RecipeListener(val clickListener: (recipe: Recipe, position: Int) -> Unit) {
    fun onClick(recipe: Recipe, position: Int) = clickListener(recipe, position)
}