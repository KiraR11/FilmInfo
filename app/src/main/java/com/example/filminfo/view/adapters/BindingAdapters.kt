package com.example.filminfo.view.adapters

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
    @BindingAdapter("app:imageUrl", "app:defaultImage")
    @JvmStatic
    fun loadImage(view: ImageView, imageUrl: String?, defaultImage : Drawable) {
        if (imageUrl.isNullOrEmpty()) {
            view.setImageDrawable(defaultImage)
        } else {
            Picasso.get().load(Uri.parse(imageUrl)).into(view)
        }
    }

    //app:defaultImage="@drawable/not_image_film"




}