package com.buffup.sdk.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.buffup.sdk.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

object ImageLoaderUtils {

    fun loadImage(context: Context?, imgUrl: String, imageView: ImageView) {
        context?.let { ctx ->
            Glide.with(ctx)
                .load(imgUrl)
                .apply(requestOptions)
                .listener(requestListener)
                .into(imageView)
        }
    }

    private val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(300, 600)

    private val requestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?, model: Any?, target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            Log.e("ImageLoaderUtils", e?.message)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

    }
}