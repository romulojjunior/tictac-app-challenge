package com.company.tictacapp.features.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.tictacapp.R
import com.company.tictacapp.common.models.TicTacMapping

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
        println(tictacMapping)
    }

    private fun loadParams() {
        intent?.apply {
            if (hasExtra(PARAM_MAPPING)) {
                tictacMapping = getSerializableExtra(PARAM_MAPPING) as TicTacMapping
            }
        }
    }
}