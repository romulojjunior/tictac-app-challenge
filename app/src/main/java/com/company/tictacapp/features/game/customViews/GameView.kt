package com.company.tictacapp.features.game.customViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.company.tictacapp.common.models.PlayerChoice
import com.company.tictacapp.common.models.TicTacMapping
import kotlin.math.min

class GameViewPosition(var i: Int, val j: Int)

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var ticTacMapping: TicTacMapping? = null
    private var touchX: Float? = null
    private var touchY: Float? = null
    private var bestPositionToUser: GameViewPosition? = null

    // Game callbacks

    var onSelectedPosition: (GameViewPosition) -> Unit = {}

    fun initializerBoard(ticTacMapping: TicTacMapping?) {
        this.ticTacMapping = ticTacMapping
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderBoard(canvas)
        renderBestPosition(canvas, bestPositionToUser)
        renderGameItems(canvas)
        renderTouchedArea(canvas)
    }

    fun recommendBestPositionToUser(i: Int, j: Int) {
        bestPositionToUser = GameViewPosition(i, j)
        invalidate()
    }

    private fun renderBestPosition(canvas: Canvas?, gameViewPosition: GameViewPosition?) {
        ticTacMapping?.let {
            if (it.currentPlayer == it.aiUserPlayer) {
                gameViewPosition?.apply {
                    val paint = Paint()
                    paint.color = Color.GREEN
                    paint.strokeWidth = 10f
                    drawCircleItemByPosition(canvas, paint, i, j)
                }
            }
        }
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
        val paint = Paint()
        paint.strokeWidth = 10f

        if (playerChoice == PlayerChoice.x) {
            paint.color = Color.RED
        } else if (playerChoice == PlayerChoice.o) {
            paint.color = Color.BLUE
        }
        drawCircleItemByPosition(canvas = canvas, paint = paint, i = i, j = j)
    }

    private fun renderTouchedArea(canvas: Canvas?) {
        if (touchX != null && touchY != null) {
            mapTouchedAreaToGamePosition(touchX!!, touchY!!)?.let { position ->
                ticTacMapping?.let { ticTacMapping ->
                    ticTacMapping.setPlayerChoice(
                        position.i,
                        position.j,
                        ticTacMapping.getCurrentPlayerChoice())
                }

                onSelectedPosition(position)
            }
            touchX = null
            touchY = null
            invalidate()
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

    private fun drawCircleItemByPosition(canvas: Canvas?, paint: Paint, i: Int, j: Int, radius: Float = 20f) {
        val minMeasured = min(measuredWidth, measuredHeight)
        val positionY = ((minMeasured / 6) + ((minMeasured / 3) * i)).toFloat()
        val positionX = ((minMeasured / 6) + ((minMeasured / 3) * j)).toFloat()

        canvas!!.drawCircle(positionX, positionY, radius, paint)
    }

    private fun mapTouchedAreaToGamePosition(x: Float, y: Float) : GameViewPosition? {
        val minMeasured = min(measuredWidth, measuredHeight)

        if (x > minMeasured || y > minMeasured) {
            return null
        }

        val squareUnit = (minMeasured / 3).toFloat()
        val cornerX: Float = squareUnit
        val cornerY: Float = squareUnit

        for (indexJ in 1..3) {
            for (indexI in 1..3) {
                val positionX = indexI -1
                val positionY = indexJ -1
                val fullCornerX = (cornerX * indexJ)
                val fullCornerY = (cornerY * indexI)

                if (fullCornerX >= x && fullCornerY >= y) {
                    return GameViewPosition(positionX, positionY)
                }
            }
        }

        return null
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val result =  super.onTouchEvent(event)
        event?.apply {
            // Validate game board area
            val minMeasured = min(measuredWidth, measuredHeight)
            if (minMeasured > event.x && minMeasured > event.y) {
                touchX = event.x
                touchY = event.y
            }

            invalidate()
        }
        return result
    }
}