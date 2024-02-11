package com.example.bullseye.ui.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.navigation.findNavController
import com.example.bullseye.R
import com.example.bullseye.Screen
import com.example.bullseye.model.GameType
import com.example.bullseye.model.Player
import com.example.bullseye.model.SaveGame
import com.example.bullseye.ui.components.MenuIconButton
import com.example.bullseye.util.dpToPx
import com.example.bullseye.values.StringProvider
import java.util.*
import kotlin.math.abs

@SuppressLint("ViewConstructor")
class GameView(
    context: Context,
    private val onSaveGame: (SaveGame) -> Unit,
    private val onResetGame: () -> Unit
) :
    LinearLayout(context) {

    private val promptTextView = TextView(context).apply {
        text = StringProvider.Game.prompt
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val slider = SeekBar(context).apply {
        max = 100
        progressBackgroundTintList = ColorStateList.valueOf(Color.WHITE)
        progressTintList = ColorStateList.valueOf(Color.parseColor("#00ff00"))
        val thumbDrawable = ContextCompat.getDrawable(context, R.drawable.slider_thumb_normal)
        thumbDrawable?.let {
            val resizedThumb = Bitmap.createScaledBitmap(
                (it as BitmapDrawable).bitmap,
                10.dpToPx, 10.dpToPx,
                false
            )

            thumb = BitmapDrawable(resources, resizedThumb)
        }
    }
    private val minimumValueTextView = TextView(context).apply {
        text = StringProvider.Game.minimumValue
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val maximumValueTextView = TextView(context).apply {
        text = StringProvider.Game.maximumValue
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val sliderView = LinearLayout(context).apply {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        addView(minimumValueTextView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(slider, LayoutParams((Screen.widthPixels / 3) * 2, WRAP_CONTENT))
        addView(maximumValueTextView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        setPadding(0, 20.dpToPx, 0, 20.dpToPx)
    }
    private val hitMeButton = Button(context).apply {
        text = StringProvider.Game.button
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 10F
            colors = intArrayOf(Color.parseColor("#f88131"), Color.parseColor("#fdc659"))
            orientation = GradientDrawable.Orientation.TOP_BOTTOM
        }
        setPadding(0, 10.dpToPx, 0, 10.dpToPx)
    }

    private val scoreTextView = TextView(context).apply {
        id = generateViewId()
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = StringProvider.Game.score
    }

    private val roundTextView = TextView(context).apply {
        id = generateViewId()
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = StringProvider.Game.round
    }
    private val playerTextView = TextView(context).apply {
        id = generateViewId()
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = StringProvider.Game.player
    }

    private val scoreBoardButton = MenuIconButton(context).apply {
        id = generateViewId()
        setIcon(
            ContextCompat.getDrawable(
                context, R.drawable.add_person
            )
        )
        setOnClickListener {

        }
    }
    private val hintsLine = LinearLayout(context).apply {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        setPadding(0, 20.dpToPx, 0, 0)
        val layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            setMargins(30.dpToPx, 0, 0, 0)
        }
        addView(scoreBoardButton, LayoutParams(30.dpToPx, 30.dpToPx))
        addView(scoreTextView, layoutParams)
        addView(playerTextView, layoutParams)
        addView(roundTextView, layoutParams)
    }


    private var targetNumber = 0
    private var scrollPosition = 50
    private var currentRound = 1
    private var currentPlayer = 0
    private var players = listOf<Player>()
    private var gameType: GameType = GameType(listOf(Player("", 0)), 99)

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        hitMeButton.setOnClickListener {
            calculateScore()
            targetNumber = Random().nextInt(101)
            addingRound()
            setupGame()
            saveGame()
        }

        addView(promptTextView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(sliderView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(hitMeButton, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(hintsLine, LayoutParams(MATCH_PARENT, WRAP_CONTENT))
    }


    fun saveGame() {
        onSaveGame(
            SaveGame(
                this.targetNumber,
                slider.progress,
                this.currentRound,
                this.currentPlayer,
                gameType.copy(playerList = players),
            )
        )
    }

    fun loadGame(saveGame: SaveGame) {
        this.targetNumber = saveGame.targetNumber
        this.scrollPosition = saveGame.scrollPosition
        this.currentRound = saveGame.currentRound
        this.currentPlayer = saveGame.currentPlayer
        this.players = saveGame.gameType.playerList
        this.gameType = saveGame.gameType
        setupGame()
    }

    private fun setupGame() {
        promptTextView.text = StringProvider.Game.prompt + targetNumber
        slider.progress = scrollPosition
        scoreTextView.text = StringProvider.Game.score + players[currentPlayer].point.toString()
        roundTextView.text = StringProvider.Game.round + currentRound.toString()
        if (players.size > 1) playerTextView.text =
            StringProvider.Game.player + players[currentPlayer].name
        else playerTextView.isGone = true
    }

    private fun addingRound() {
        if (currentRound + 1 > gameType.roundCount) {
            if (currentPlayer == players.size - 1) {
                currentRound = 1
                scoreTextView
                players[currentPlayer].point = 0
                onResetGame()
                findNavController().navigateUp()
            } else {
                currentPlayer++
                currentRound = 1
            }
        } else {
            currentRound++
        }
    }

    private fun calculateScore() {
        val difference = abs(slider.progress - targetNumber)
        var points = 100 - difference
        val popUpTitle: String

        when {
            difference == 0 -> {
                popUpTitle = "Perfect!"
                points += 100
            }
            difference < 5 -> {
                popUpTitle = "You almost had it!"
                if (difference == 1) {
                    points += 50
                }
            }
            difference < 10 -> {
                popUpTitle = "Pretty good!"
            }
            else -> {
                popUpTitle = "Not even close..."
            }
        }
        Toast.makeText(context, "$popUpTitle  $points", Toast.LENGTH_SHORT).show()
        players[currentPlayer].point += points
    }

}