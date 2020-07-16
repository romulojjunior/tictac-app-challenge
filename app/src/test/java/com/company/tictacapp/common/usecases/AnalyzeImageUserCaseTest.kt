package com.company.tictacapp.common.usecases

import com.company.tictacapp.common.analyzer.ImageAnalyzer
import com.company.tictacapp.common.models.BitmapImage
import com.company.tictacapp.common.models.ImageColor
import com.company.tictacapp.common.models.PlayerChoice
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AnalyzeImageUserCaseTest {
    private lateinit var analyzeImageUserCase: AnalyzeImageUserCase
    private lateinit var analyzer: ImageAnalyzer
    private val redImageColor = ImageColor(red = 255, alpha = 255)
    private val blueImageColor = ImageColor(blue = 255, green = 15, alpha = 255)

    @Mock
    private lateinit var bitmapImage : BitmapImage

    @Before
    fun before() {
        analyzer = ImageAnalyzer()
        analyzeImageUserCase =  AnalyzeImageUserCase(analyzer)
    }

    /*
        x |   |
          | o |
          |   |x
    */

    @Test
    fun execute() {
        Mockito.`when`(bitmapImage.getColor(100, 50)).thenReturn(redImageColor)
        Mockito.`when`(bitmapImage.getColor(300, 350)).thenReturn(blueImageColor)
        Mockito.`when`(bitmapImage.getColor(500, 450)).thenReturn(redImageColor)

        val result = analyzeImageUserCase.execute(bitmapImage)

        assertEquals(PlayerChoice.x, result.matrix[0][0])
        assertEquals(PlayerChoice.o, result.matrix[1][1])
        assertEquals(PlayerChoice.x, result.matrix[2][2])
    }
}