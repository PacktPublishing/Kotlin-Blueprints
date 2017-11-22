package com.kotlinblueprint.besocial.util

import android.graphics.Bitmap
import android.widget.ImageView
import com.kotlinblueprint.besocial.R
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer

/**
 * Created by hardik.trivedi on 18/09/17.
 */
fun ImageView.setRoundedImageOption(url: String) {
    val imageOptions = DisplayImageOptions.Builder()
            .displayer(RoundedBitmapDisplayer(1000))
            .showImageOnLoading(R.drawable.no_img_bg)
            .showImageForEmptyUri(R.drawable.no_img_bg)
            .showImageOnFail(R.drawable.no_img_bg)
            .cacheInMemory(true)
            .cacheOnDisk(false)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build()
    ImageLoader.getInstance().displayImage(url, this, imageOptions)
}

fun ImageView.setImageLazy(url: String) {
    val imageOptions = DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.no_img_bg)
            .showImageForEmptyUri(R.drawable.no_img_bg)
            .showImageOnFail(R.drawable.no_img_bg)
            .cacheInMemory(true)
            .cacheOnDisk(false)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build()
    ImageLoader.getInstance().displayImage(url, this, imageOptions)
}