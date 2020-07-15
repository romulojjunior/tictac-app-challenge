package com.company.tictacapp.common.models

import android.graphics.Bitmap
import android.util.Log

class BitmapImage(var bitmap: Bitmap?) {

    fun getColor(x: Int, y: Int) : ImageColor? {
        return bitmap?.let {
            return ImageColor.fromPixel(it.getPixel(x, y))
        }
    }

    fun debugColor(x: Int = 100, y: Int = 80) {
        getColor(x, y)?.apply {
            Log.d("BitmapImage", "R $red  G $green B $blue A $alpha")
        }
    }
}