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

    @Test
    fun gameTest04() {
        val expected = arrayListOf(2, 2) // x, y
        val ticTacMapping = TicTacMapping(
            matrix = arrayListOf<ArrayList<PlayerChoice?>>(
                arrayListOf(PlayerChoice.o, null, PlayerChoice.x),
                arrayListOf(null, PlayerChoice.o, null),
                arrayListOf<PlayerChoice?>(null, null, null)
            ))

        val result = gameAnalyzer.findBestPosition(ticTacMapping)

        expected.forEachIndexed { index, i ->
            assertEquals(i, result[index])
        }
    }

    @Test
    fun gameTest05() {
        val expected = arrayListOf(1, 1) // x, y
        val ticTacMapping = TicTacMapping(
            matrix = arrayListOf<ArrayList<PlayerChoice?>>(
                arrayListOf(PlayerChoice.o, null, null),
                arrayListOf(null, null, null),
                arrayListOf<PlayerChoice?>(null, null, null)
            ))

        val result = gameAnalyzer.findBestPosition(ticTacMapping)

        expected.forEachIndexed { index, i ->
            assertEquals(i, result[index])
        }
    }

    @Test
    fun gameTestRandom() {
        val expected = arrayListOf(2, 1) // x, y
        val ticTacMapping = TicTacMapping(
            matrix = arrayListOf<ArrayList<PlayerChoice?>>(
                arrayListOf(PlayerChoice.o, PlayerChoice.x, PlayerChoice.o),
                arrayListOf(PlayerChoice.o, PlayerChoice.x, PlayerChoice.x),
                arrayListOf<PlayerChoice?>(null, null, PlayerChoice.o)
            ))

        val result = gameAnalyzer.findBestPosition(ticTacMapping)

        expected.forEachIndexed { index, i ->
            assertEquals(i, result[index])
        }
    }
}
