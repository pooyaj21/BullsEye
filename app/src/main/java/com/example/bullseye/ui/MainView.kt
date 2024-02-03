package com.example.bullseye.ui

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.navigation.findNavController
import com.example.bullseye.R
import com.example.bullseye.Screen
import com.example.bullseye.ui.components.MenuButton
import com.example.bullseye.util.dpToPx
import com.example.bullseye.values.StringProvider

class MainView(context: Context) : LinearLayout(context) {

    private val newGameButton = MenuButton(context).apply {
        text = StringProvider.MainMenu.newSingle
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            findNavController().navigate(R.id.gameFragment)
        }
    }
    private val loadGameButton = MenuButton(context).apply {
        text = StringProvider.MainMenu.loadGame
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            findNavController().navigate(R.id.gameFragment)
        }
    }
    private val multiPlayButton = MenuButton(context).apply {
        text = StringProvider.MainMenu.newMulti
        textAlignment = TEXT_ALIGNMENT_CENTER
        setOnClickListener {
            findNavController().navigate(R.id.multiplyFragment)
        }
    }
    private val aboutGameButton = MenuButton(context).apply {
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