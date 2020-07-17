package com.company.tictacapp.common.models

import java.io.Serializable

class TicTacMapping(var matrix: ArrayList<ArrayList<PlayerChoice?>> = arrayListOf()) : Serializable {
    var aiUserPlayer: PlayerChoice = PlayerChoice.x
    var opponentPlayer: PlayerChoice = PlayerChoice.o

    fun getPlayerChoiceByPosition(i: Int, j: Int): PlayerChoice? {
        if (matrix.count() > i && matrix[i].count() > j) {
            return matrix[i][j]
        }

        return null
    }

    fun setPlayerChoice(i: Int, j: Int, playerChoice: PlayerChoice?) : Boolean {
        if (matrix.count() > i && matrix[i].count() > j) {
            matrix[i][j] = playerChoice
            return true
        }
        return false
    }

    fun isPositionAvailable(i: Int, j: Int): Boolean {
        if (matrix.count() > i && matrix[i].count() > j) {
            return matrix[i][j] == null
        }
        return false
    }

    fun checkWinner(): PlayerChoice? {
        val matrixSize = matrix.size -1
        var playerChoice: PlayerChoice? = null

        if (compareDiagonalLeft()) {
            playerChoice = matrix[0][0]
        }
        if (compareDiagonalRight()) {
            playerChoice = matrix[0][2]
        }

        if (checkTie()) {
            playerChoice = PlayerChoice.none
        }

        for(index in 0..matrixSize) {
            if (compareRow(index)) playerChoice = matrix[index][0]

            if (compareColumn(index)) playerChoice = matrix[0][index]
        }

        return playerChoice
    }

    private fun compareRow(number: Int) : Boolean {
        if (number >= matrix.count()) return false

        return (matrix[number][0] == matrix[number][1] && matrix[number][0]  == matrix[number][2])
    }

    private fun compareColumn(number: Int) : Boolean {
        if (number >= matrix.count()) return false
        return (matrix[0][number] == matrix[1][number] && matrix[0][number]  == matrix[2][number])
    }

    private fun compareDiagonalRight() : Boolean {
        return (matrix[0][2] == matrix[1][1] && matrix[0][2]  ==  matrix[2][0])

    }

    private fun compareDiagonalLeft() : Boolean {
        return (matrix[0][0] == matrix[1][1] && matrix[0][0]  == matrix[2][2])
    }


    private fun checkTie(): Boolean {
        var isTie = true
        for(i in 0..2) {
            for (j in 0..2) {
                if (matrix[i][j] == null) {
                    isTie = false
                    break
                }
            }
            if (!isTie) break
        }

        return isTie
    }

    override fun toString(): String {
        return """
        ${matrix[0][0]} | ${matrix[0][1]} | ${matrix[0][2]} |
        ${matrix[1][0]} | ${matrix[1][1]} | ${matrix[1][2]} |
        ${matrix[2][0]} | ${matrix[2][1]} | ${matrix[2][2]} |
        """
    }

    fun debug() {
        println("""
        ${formatLabel(matrix[0][0])} | ${formatLabel(matrix[0][1])} | ${formatLabel(matrix[0][2])} |
        ${formatLabel(matrix[1][0])} | ${formatLabel(matrix[1][1])} | ${formatLabel(matrix[1][2])} |
        ${formatLabel(matrix[2][0])} | ${formatLabel(matrix[2][1])} | ${formatLabel(matrix[2][2])} |
        """)
    }

    private fun formatLabel(value: Any?): String {
        if (value == null) {
            return " "
        }
        return "$value"
    }
}