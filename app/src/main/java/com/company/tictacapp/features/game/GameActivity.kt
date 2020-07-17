package com.company.tictacapp.features.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.company.tictacapp.R
import com.company.tictacapp.common.analyzer.GameAnalyzer
import com.company.tictacapp.common.models.PlayerChoice
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
        setPlayerTurnText(tictacMapping.aiUserPlayer)
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
            tictacMapping.nextPlayerTurn()
            setPlayerTurnText(tictacMapping.currentPlayer)
            analyzeGame(tictacMapping)
        }
    }

    fun setPlayerTurnText(playerChoice: PlayerChoice) {
        playerTurnTextView.text = ("Turn: $playerChoice")

    }

    private fun analyzeGame(tictacMapping: TicTacMapping) {


        GlobalScope.launch(Dispatchers.IO) {
            async {
                val winner = tictacMapping.checkWinner()
                if (winner != null) {
                    runOnUiThread {
                        Toast.makeText(application, "Winner $winner", Toast.LENGTH_LONG).show()
                    }
                }

                val gameAnalyzer = GameAnalyzer()
                val result = gameAnalyzer.findBestPosition(tictacMapping)
                runOnUiThread {
                    if (result.isNotEmpty()) {
                        gameView.recommendBestPositionToUser(result[0],result[1])
                    } else {
                        Toast.makeText(application, "GameOver", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}