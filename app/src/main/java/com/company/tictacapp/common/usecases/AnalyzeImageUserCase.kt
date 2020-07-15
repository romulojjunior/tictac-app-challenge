package com.company.tictacapp.common.usecases

import com.company.tictacapp.analyzer.ImageAnalyzer
import com.company.tictacapp.common.models.BitmapImage
import com.company.tictacapp.common.models.TicTocMapping


class AnalyzeImageUserCase(var imageAnalyzer: ImageAnalyzer = ImageAnalyzer()) : UseCaseBase<BitmapImage, TicTocMapping>() {
    override fun execute(param: BitmapImage): TicTocMapping {
        val playerChoice11 = imageAnalyzer.findPlayerChoice(param, startPointX = 100, startPointY = 0, endPointX = 100, endPointY = 190)
        val playerChoice12 = imageAnalyzer.findPlayerChoice(param, startPointX = 300, startPointY = 0, endPointX = 300, endPointY = 190)
        val playerChoice13 = imageAnalyzer.findPlayerChoice(param, startPointX = 500, startPointY = 0, endPointX = 500, endPointY = 190)

        val playerChoice21 = imageAnalyzer.findPlayerChoice(param, startPointX = 100, startPointY = 200, endPointX = 100, endPointY = 399)
        val playerChoice22 = imageAnalyzer.findPlayerChoice(param, startPointX = 300, startPointY = 200, endPointX = 300, endPointY = 399)
        val playerChoice23 = imageAnalyzer.findPlayerChoice(param, startPointX = 500, startPointY = 200, endPointX = 500, endPointY = 399)

        val playerChoice31 = imageAnalyzer.findPlayerChoice(param, startPointX = 100, startPointY = 400, endPointX = 100, endPointY = 499)
        val playerChoice32 = imageAnalyzer.findPlayerChoice(param, startPointX = 300, startPointY = 400, endPointX = 300, endPointY = 499)
        val playerChoice33 = imageAnalyzer.findPlayerChoice(param, startPointX = 500, startPointY = 400, endPointX = 500, endPointY = 499)

        return TicTocMapping(matrix = listOf(
            listOf(playerChoice11, playerChoice12, playerChoice13).toList(),
            listOf(playerChoice21, playerChoice22, playerChoice23).toList(),
            listOf(playerChoice31, playerChoice32, playerChoice33).toList()
        ))
    }
}