package com.company.tictacapp.common.analyzer

import com.company.tictacapp.common.models.BitmapImage
import com.company.tictacapp.common.models.ImageColor
import com.company.tictacapp.common.models.PlayerChoice
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageAnalyzerTest {
    private lateinit var analyzer :ImageAnalyzer
    private val redImageColor = ImageColor(red = 255, alpha = 255)
    private val blueImageColor = ImageColor(blue = 255, green = 15, alpha = 255)

    @Mock
    private lateinit var bitmapImage : BitmapImage

    @Before
    fun before() {
        analyzer = ImageAnalyzer()
    }

    @Test
    fun findPlayerChoice_WhenChoiceIsX() {
        `when`(bitmapImage.getColor(100, 50)).thenReturn(redImageColor)

        val result: PlayerChoice? = analyzer.findPlayerChoice(
            bitmapImage,
            startPointX = 100,
            startPointY = 0,
            endPointX = 100,
            endPointY = 190
        )

        assertEquals(PlayerChoice.x, result)
    }

    @Test
    fun findPlayerChoice_WhenChoiceIsO() {
        `when`(bitmapImage.getColor(100, 50)).thenReturn(blueImageColor)

        val result: PlayerChoice? = analyzer.findPlayerChoice(
            bitmapImage,
            startPointX = 100,
            startPointY = 0,
            endPointX = 100,
            endPointY = 190
        )

        assertEquals(PlayerChoice.o, result)
    }

    @Test
    fun findPlayerChoice_WhenChoiceIsEmpty() {
        `when`(bitmapImage.getColor(100, 50)).thenReturn(null)

        val result: PlayerChoice? = analyzer.findPlayerChoice(
            bitmapImage,
            startPointX = 100,
            startPointY = 0,
            endPointX = 100,
            endPointY = 190
        )

        assertEquals(null, result)
    }
}