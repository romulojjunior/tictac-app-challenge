package com.company.tictacapp.common.helpers

import android.graphics.Color

class ImageColor(var red: Int, var green: Int, var blue: Int, var alpha:Int = 0) {

    companion object {
        fun fromPixel(pixel: Int) : ImageColor {
            return ImageColor(
                red = Color.red(pixel),
                green = Color.green(pixel),
                blue = Color.blue(pixel),
                alpha = Color.alpha(pixel)
            )
        }
    }
}