package com.company.tictacapp.common.models

import android.graphics.Bitmap
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BitmapImageTest {
    lateinit var bitmapImage: BitmapImage
    var imageColor: ImageColor = ImageColor()

    @Mock
    private lateinit var bitmap : Bitmap

    @Before
    fun before() {
        bitmapImage = BitmapImage(bitmap)
        imageColor.withPixel(-16773121)
    }

    @Test
    fun getColor() {
        val pixelValue = -16773121
        Mockito.`when`(bitmap.getPixel(100, 50)).thenReturn(pixelValue)
        val result = bitmapImage.getColor(100, 50)

        result?.apply {
            assertEquals(imageColor.red, red)
            assertEquals(imageColor.green, green)
            assertEquals(imageColor.blue, blue)
            assertEquals(imageColor.alpha, alpha)
        }
    }
}