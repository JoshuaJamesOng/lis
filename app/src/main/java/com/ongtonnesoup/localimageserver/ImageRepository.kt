package com.ongtonnesoup.localimageserver

import android.content.Context

private const val LOCAL_IMAGE_FILENAME = "previously-downloaded-image.jpeg"

class ImageRepository(private val context: Context) {

    fun loadImage(originalSrc: String): LocalImage {
        // TODO Here we'd need to do some mapping between originalSrc and local file URI
        val inputStream = context.assets.open(LOCAL_IMAGE_FILENAME)
        return LocalImage(inputStream)
    }

}