package com.example.bullseye.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.bullseye.R
import com.example.bullseye.Screen
import com.example.bullseye.model.GameType
import com.example.bullseye.ui.components.MenuTextButton
import com.example.bullseye.util.dpToPx
import com.example.bullseye.values.StringProvider

@SuppressLint("ViewConstructor")
class MainView(context: Context) : LinearLayout(context) {

    private val newGameButton = MenuTextButton(context).apply {
        text = StringProvider.MainMenu.newSingle
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            val gameType = GameType(listOf(""), 1)
            val args = bundleOf("gameType" to gameType)
            findNavController().navigate(R.id.gameFragment, args)
        }
    }
    private val loadGameButton = MenuTextButton(context).apply {
        text = StringProvider.MainMenu.loadGame
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            findNavController().navigate(R.id.gameFragment)
        }
    }
    private val multiPlayButton = MenuTextButton(context).apply {
        text = StringProvider.MainMenu.newMulti
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            findNavController().navigate(R.id.multiplyFragment)
        }
    }
    private val aboutGameButton = MenuTextButton(context).apply {
        text = StringProvider.MainMenu.about
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            findNavController().navigate(R.id.infoFragment)
        }
    }

    private val buttonsSize = Screen.widthPixels / 3

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER

        val layoutParams = LayoutParams(buttonsSize, WRAP_CONTENT).apply {
            setMargins(0, 5.dpToPx, 0, 5.dpToPx)
        }

        addView(newGameButton, layoutParams)
        addView(loadGameButton, layoutParams)
        addView(multiPlayButton, layoutParams)
        addView(aboutGameButton, layoutParams)
    }

}