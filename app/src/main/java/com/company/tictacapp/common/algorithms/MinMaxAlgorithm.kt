package com.company.tictacapp.common.algorithms

import com.company.tictacapp.common.models.PlayerChoice
import com.company.tictacapp.common.models.TicTacMapping
import kotlin.math.max
import kotlin.math.min

fun minMaxAlgorithm(ticTacMapping: TicTacMapping, deep: Int, isMax: Boolean, playerChoice: PlayerChoice?): Int {
    ticTacMapping.debug() //TODO: remove this line
    println(playerChoice) //TODO: remove this line

    val winner = ticTacMapping.checkWinner()

    if (winner != null) {
       return when(winner) {
            PlayerChoice.x -> return 10
           PlayerChoice.o -> return -10
           PlayerChoice.none -> 0
        }
    }

    if (isMax) {
        var bestScore = Int.MIN_VALUE
        (0..2).forEach { i ->
            (0..2).forEach { j ->
                if (ticTacMapping.isPositionAvailable(i, j)) {
                    ticTacMapping.setPlayerChoice(i, j, playerChoice)
                    val score = minMaxAlgorithm(ticTacMapping, deep + 1, false, replaceCurrentPlayer(playerChoice))
                    ticTacMapping.setPlayerChoice(i, j, null)
                    bestScore = max(score, bestScore)
                }
            }
        }
        return bestScore
    } else {
        var bestScore = Int.MAX_VALUE
        (0..2).forEach { i ->
            (0..2).forEach { j ->
                if (ticTacMapping.isPositionAvailable(i, j)) {
                    ticTacMapping.setPlayerChoice(i, j, playerChoice)
                    val score = minMaxAlgorithm(ticTacMapping, deep + 1, true, replaceCurrentPlayer(playerChoice))
                    ticTacMapping.setPlayerChoice(i, j, null)
                    bestScore = min(score, bestScore)
                }
            }
        }
        return bestScore
    }
}

fun replaceCurrentPlayer(playerChoice: PlayerChoice?): PlayerChoice? {
    return if (playerChoice == PlayerChoice.x) PlayerChoice.o else PlayerChoice.x
}
