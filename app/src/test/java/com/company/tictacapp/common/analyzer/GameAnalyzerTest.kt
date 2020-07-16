package com.company.tictacapp.common.analyzer

import com.company.tictacapp.common.models.*
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GameAnalyzerTest {
    private lateinit var gameAnalyzer :GameAnalyzer

    @Before
    fun before() {
        gameAnalyzer = GameAnalyzer()
    }

    @Test
    fun gameTest01() {
        val expected = arrayListOf(1, 2) // x, y
        val ticTacMapping = TicTacMapping(
            matrix = arrayListOf(
                arrayListOf(PlayerChoice.x, null, null),
                arrayListOf(PlayerChoice.o, PlayerChoice.o, null),
                arrayListOf(PlayerChoice.x, null, null)
        ))

        val result = gameAnalyzer.findBestPosition(ticTacMapping)

        expected.forEachIndexed { index, i ->
            assertEquals(i, result[index])
        }
    }

    @Test
    fun gameTest02() {
        val expected = arrayListOf(1, 1) // x, y
        val ticTacMapping = TicTacMapping(
            matrix = arrayListOf(
                arrayListOf(PlayerChoice.x, PlayerChoice.o, null),
                arrayListOf(PlayerChoice.x, null, null),
                arrayListOf(PlayerChoice.o, null, null)
            ))

        val result = gameAnalyzer.findBestPosition(ticTacMapping)

        expected.forEachIndexed { index, i ->
            assertEquals(i, result[index])
        }
    }

    @Test
    fun gameTest03() {
        val expected = arrayListOf(1,0) // x, y
        val ticTacMapping = TicTacMapping(
            matrix = arrayListOf<ArrayList<PlayerChoice?>>(
                arrayListOf(PlayerChoice.x, null, PlayerChoice.o),
                arrayListOf(null, PlayerChoice.o, null),
                arrayListOf<PlayerChoice?>(PlayerChoice.x, null, null)
            ))

        val result = gameAnalyzer.findBestPosition(ticTacMapping)

        expected.forEachIndexed { index, i ->
            assertEquals(i, result[index])
        }
    }
}
