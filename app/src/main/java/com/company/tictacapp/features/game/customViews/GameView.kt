package com.company.tictacapp.features.game.customViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class GameView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        renderBoard(canvas)
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