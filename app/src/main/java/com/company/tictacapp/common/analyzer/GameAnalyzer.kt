package com.company.tictacapp.common.analyzer

import com.company.tictacapp.common.algorithms.minMaxAlgorithm
import com.company.tictacapp.common.models.PlayerChoice
import com.company.tictacapp.common.models.TicTacMapping
import kotlin.math.max

// PlayerChoice.x = 10
// PlayerChoice.o = -10
// Tie = 0

class GameAnalyzer {

    fun findBestPosition(ticTacMapping: TicTacMapping) : Array<Int> {
        var bestScore = Int.MIN_VALUE
        var bestPosition: Array<Int> = arrayOf()
        ticTacMapping.debug()
        runGame(ticTacMapping) { i, j ->
            if (ticTacMapping.isPositionAvailable(i, j)) {
                ticTacMapping.setPlayerChoice(i,j, ticTacMapping.aiUserPlayer)
                val score = minMaxAlgorithm(ticTacMapping, deep = 0, isMax = false, playerChoice = ticTacMapping.opponentPlayer)
                ticTacMapping.setPlayerChoice(i,j, null)

                if (score > bestScore) {
                    bestScore = score
                    bestPosition = arrayOf(i, j)
                }
            }
        }

        return bestPosition // x,y
    }

    private fun runGame(ticTacMapping: TicTacMapping, onGame: (i: Int, j: Int) -> Unit) {
        val matrixSize: Int = ticTacMapping.matrix.count() -1
        val column: Int = matrixSize
        val row: Int = matrixSize

        for (i in 0..row) {
            for (j in 0..column) {
                onGame(i, j)
            }
        }
    }
}