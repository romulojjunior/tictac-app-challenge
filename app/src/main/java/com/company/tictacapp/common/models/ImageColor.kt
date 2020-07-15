package com.company.tictacapp.common.models

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

    fun compareColor(red: Int, green: Int, blue: Int, alpha:Int = 0) : Boolean {
        return this.red == red  && this.green == green && this.blue == blue && this.alpha == alpha
    }

    fun isBlue() : Boolean = compareColor(red = 0, green = 15, blue = 255, alpha = 255)
    fun isRed() : Boolean = compareColor(red = 255, green = 0, blue = 0, alpha = 255)
}