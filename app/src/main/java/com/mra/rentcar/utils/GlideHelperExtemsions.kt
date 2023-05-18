package com.mra.rentcar.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.mra.testrantecarapp.R

fun ImageView.showWithGlide(imgUrl: String?, option: RequestOptions? = null) {
    val options = option
        ?: RequestOptions()
            .error(R.drawable.ic_launcher_foreground)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()

    Glide.with(context)
        .load(imgUrl)
        .apply(options)
        .into(this)
}