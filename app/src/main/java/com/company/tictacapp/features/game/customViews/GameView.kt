package com.company.tictacapp.features.game.customViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.company.tictacapp.common.models.PlayerChoice
import com.company.tictacapp.common.models.TicTacMapping
import kotlin.math.min

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var ticTacMapping: TicTacMapping? = null

    fun initializerBoard(ticTacMapping: TicTacMapping?) {
        this.ticTacMapping = ticTacMapping
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderBoard(canvas)
        renderGameItems(canvas)
    }

    private fun renderGameItems(canvas: Canvas?) {
        ticTacMapping?.let { mapping ->
            for (i in 0..2) {
                for (j in 0..2) {
                    val playerChoice = mapping.getPlayerChoiceByPosition(i, j)
                    if (playerChoice != null) {
                        renderPlayerChoiceByPosition(canvas, playerChoice, i, j)
                    }
                }
            }

        }
    }

    private fun renderPlayerChoiceByPosition(canvas: Canvas?, playerChoice: PlayerChoice, i: Int, j: Int) {
        val minMeasured = min(measuredWidth, measuredHeight)
        val positionY = ((minMeasured / 6) + ((minMeasured / 3) * i)).toFloat()
        val positionX = ((minMeasured / 6) + ((minMeasured / 3) * j)).toFloat()

        if (playerChoice == PlayerChoice.x) {
            val paintRed = Paint()
            paintRed.color = Color.RED

            paintRed.strokeWidth = 10f
            val radius = 20f
            canvas!!.drawCircle(positionX, positionY, radius, paintRed)
        } else if (playerChoice == PlayerChoice.o) {
            val paintBlue = Paint()
            paintBlue.color = Color.BLUE
            paintBlue.strokeWidth = 10f

            val radius = 20f
            canvas!!.drawCircle(positionX, positionY, radius, paintBlue)
        }
    }

    private fun renderBoard(canvas: Canvas?) {
        val minMeasured = min(measuredWidth, measuredHeight)
        val paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 10f

        val columnWith: Float = (minMeasured / 3).toFloat()
        val columnHeight: Float = (minMeasured).toFloat()

        val rowWith: Float = (minMeasured).toFloat()
        val rowHeight: Float = (minMeasured / 3).toFloat()

        (0..3).forEach { index ->
            val position = columnWith * index
            canvas?.drawLine(position,0f, position, columnHeight, paint)
        }

        (0..3).forEach { index ->
            val position = rowHeight * index
            canvas?.drawLine(0f, position, rowWith, position, paint)
        }
    }
}