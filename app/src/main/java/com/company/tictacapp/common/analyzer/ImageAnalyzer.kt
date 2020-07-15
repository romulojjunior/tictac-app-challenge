package com.company.tictacapp.common.analyzer

import com.company.tictacapp.common.models.BitmapImage
import com.company.tictacapp.common.models.PlayerChoice

// board details
// w 600 px
// h 670 px

// Icons
// w 50 px
// h 50 px


class ImageAnalyzer() {
    fun findPlayerChoice(bitmapImage: BitmapImage, startPointX: Int, startPointY: Int, endPointX: Int, endPointY: Int) : PlayerChoice? {
        var positionX = startPointX
        var positionY = startPointY
        var stop = false
        var playerChoice: PlayerChoice? = null


        while (!stop && (positionX <= endPointX || positionY <= endPointY)) {
            bitmapImage.debugColor(positionX, positionY)
            val imageColor = bitmapImage.getColor(positionX, positionY)

            if (imageColor != null) {
                if (imageColor.isRed()) {
                    playerChoice = PlayerChoice.x
                    stop = true
                } else if (imageColor.isBlue()) {
                    playerChoice = PlayerChoice.o
                    stop = true
                }
            }

            if (positionX <= endPointX) positionX += 1;

            if (positionY <= endPointY) positionY += 1;

            if (positionX == endPointX && positionY == endPointY)  stop = true;

        }

        return playerChoice
    }
}