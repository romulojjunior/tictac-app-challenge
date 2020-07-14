package com.company.tictacapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.company.tictacapp.common.ImageHelper
import com.company.tictacapp.common.models.Player
import com.company.tictacapp.common.models.PlayerType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var players : List<String>
    var currentPlayer: Player? = null

    companion object {
        const val TAG = "TicTacApp"
        const val READ_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPlayerList()
        loadScreenshotButton()
        loadPlayerSpinner()
    }

    private fun loadPlayerList() {
        players = listOf(getString(R.string.choose_your_player), "Player X", "Player O")
    }

    private fun loadScreenshotButton() {
        screenshotButton?.setOnClickListener {
            openGallery()
        }
    }

    private fun loadPlayerSpinner() {
        val layoutId = android.R.layout.simple_list_item_1
        val itemsAdapter: ArrayAdapter<String> =  ArrayAdapter<String>(this, layoutId, players)

        playerSpinner.adapter = itemsAdapter
        playerSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,  position: Int,  id: Long) {
                currentPlayer = when (position) {
                    1 -> Player(type = PlayerType.x)
                    2 -> Player(type = PlayerType.x)
                    else -> null
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
        val imageHelper = ImageHelper(this.application)
        imageHelper.loadImage(uri);
        imageHelper.debugColor()
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