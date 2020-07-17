package com.company.tictacapp.features.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.tictacapp.R
import com.company.tictacapp.common.analyzer.GameAnalyzer
import com.company.tictacapp.common.models.TicTacMapping
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    lateinit var tictacMapping: TicTacMapping

    companion object {
        private const val PARAM_MAPPING = "PARAM_MAPPING"

        fun newIntent(context: Context, ticTocMapping: TicTacMapping) : Intent {
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(PARAM_MAPPING, ticTocMapping)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        loadParams()
        loadGameView(tictacMapping)
        analyzeGame(tictacMapping)
    }

    private fun loadParams() {
        intent?.apply {
            if (hasExtra(PARAM_MAPPING)) {
                tictacMapping = getSerializableExtra(PARAM_MAPPING) as TicTacMapping
            }
        }
    }

    private fun loadGameView(tictacMapping: TicTacMapping) {
        gameView.initializerBoard(tictacMapping)
        gameView.onSelectedPosition = { position ->
            Toast.makeText(application, "Selected position: (${position.i}, ${position.j})", Toast.LENGTH_SHORT).show()
        }
    }

    private fun analyzeGame(tictacMapping: TicTacMapping) {
        playerTurnTextView.text = ("Turn: ${tictacMapping.aiUserPlayer}")
        GlobalScope.launch(Dispatchers.IO) {
            async {
                val gameAnalyzer = GameAnalyzer()
                val result = gameAnalyzer.findBestPosition(tictacMapping)
                runOnUiThread {
                    gameView.recommendBestPositionToUser(result[0],result[1])
                    Toast.makeText(application, "Best position: (${result[0]}, ${result[1]})", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}