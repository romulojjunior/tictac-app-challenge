package com.company.tictacapp.common

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.company.tictacapp.common.models.BitmapImage

class ImageHelper(private var application: Application) {
    private var bitmap: Bitmap? = null

    fun loadImage(fileUri: Uri) {
        bitmap = getBitmapImage(fileUri)
    }

    @SuppressLint("NewApi")
    private fun getBitmapImage(fileUri: Uri): Bitmap {
        return when {
            Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                application.contentResolver,
                fileUri
            )
            else -> {
                val source = ImageDecoder.createSource(application.contentResolver, fileUri)
                ImageDecoder.decodeBitmap(source)
            }
        }
    }

    fun toBitmapImage() : BitmapImage {
        return BitmapImage(bitmap = bitmap)
    }
}