package com.company.tictacapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company.tictacapp.common.analyzer.GameAnalyzer
import com.company.tictacapp.common.helpers.ImageHelper
import com.company.tictacapp.common.models.Player
import com.company.tictacapp.common.models.PlayerType
import com.company.tictacapp.common.usecases.AnalyzeImageUserCase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var players : List<String>
    var currentPlayerType: PlayerType? = null


    companion object {
        const val TAG = "TicTacApp"
        const val READ_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPlayerList()
        loadPlayerSpinner()
    }

    private fun loadPlayerList() {
        players = listOf(getString(R.string.choose_your_player), "Player X", "Player O")
    }

    private fun loadPlayerSpinner() {
        val layoutId = android.R.layout.simple_list_item_1
        val itemsAdapter: ArrayAdapter<String> =  ArrayAdapter<String>(this, layoutId, players)

        playerSpinner.adapter = itemsAdapter
        playerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,  position: Int,  id: Long) {
                when (position) {
                    1 -> {
                        currentPlayerType = PlayerType.x
                        openGallery()
                    }
                    2 -> {
                        currentPlayerType = PlayerType.o
                        openGallery()
                    }
                    else -> {
                        currentPlayerType = null
                        selectedImageView.setImageBitmap(null)
                    }
                }

                Log.d(TAG, "Current player: ${players[position]}")
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    private fun displaySelectedImage(uri: Uri) {
        selectedImageView.setImageURI(uri);
        GlobalScope.launch(Dispatchers.IO) {
            async {
                val imageHelper = ImageHelper(application)
                imageHelper.loadImage(uri);
                val analyzeImageUserCase = AnalyzeImageUserCase()
                val ticTocMapping = analyzeImageUserCase.execute(imageHelper.toBitmapImage())
                println(ticTocMapping) // TODO: remove it

                val gameAnalyzer = GameAnalyzer()
                var result = gameAnalyzer.findBestPosition(ticTocMapping)
                println(result[0])
                println(result[1])

                runOnUiThread {
                    Toast.makeText(application, "Best position: (${result[0]}, ${result[1]})", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)

        if (requestCode == READ_REQUEST_CODE && resultCode == RESULT_OK) {
            resultData?.data?.also { uri ->
                Log.i(TAG, "Uri: $uri")
                displaySelectedImage(uri)
            }
        }
    }
}