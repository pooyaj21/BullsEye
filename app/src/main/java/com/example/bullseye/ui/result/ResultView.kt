package com.example.bullseye.ui.result

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import com.example.bullseye.model.Player
import com.example.bullseye.values.StringProvider

class ResultView(context: Context) : LinearLayout(context) {
    private val titleTextView = TextView(context).apply {
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = StringProvider.Result.prompt
    }
    val playerHeader = TextView(context).apply {
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = StringProvider.Result.player
        layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
        setPadding(padding, padding, padding, padding)
    }
    val scoreHeader = TextView(context).apply {
        textAlignment = TEXT_ALIGNMENT_CENTER
        text = StringProvider.Result.score
        layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
        setPadding(padding, padding, padding, padding)
    }
    private val headerView = LinearLayout(context).apply {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        addView(playerHeader)
        addView(scoreHeader)
    }

    private val customLayoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    private val padding = 16 // Adjust as needed

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        addView(titleTextView, customLayoutParams)
        addView(headerView, customLayoutParams)
    }

    fun bindData(players: List<Player>) {
        players.forEach {
            val playerName = TextView(context).apply {
                textAlignment = TEXT_ALIGNMENT_CENTER
                text = it.name
                layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
                setPadding(padding, padding, padding, padding)
            }
            val playerScore = TextView(context).apply {
                textAlignment = TEXT_ALIGNMENT_CENTER
                text = it.point.toString()
                layoutParams = LayoutParams(0, WRAP_CONTENT, 1f)
                setPadding(padding, padding, padding, padding)
            }

            val playerLayout = LinearLayout(context).apply {
                orientation = HORIZONTAL
                layoutParams = customLayoutParams
                addView(playerName)
                addView(playerScore)
            }

            addView(playerLayout)
        }
    }
}
