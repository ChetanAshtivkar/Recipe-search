package com.recipe.search.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/**
 * Created by Chetan on 22/03/20.
 */

@BindingAdapter("app:set_picture")
fun setPicture(imageView: ImageView, imageUrl: String) {
    GlideApp.with(imageView.context)
        .load(imageUrl)
        .fitCenter()
        .centerCrop()
        .into(imageView)
}