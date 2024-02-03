package com.example.bullseye.ui.multiPlayerMenu

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.example.bullseye.R
import com.example.bullseye.Screen
import com.example.bullseye.ui.components.MenuIconButton
import com.example.bullseye.util.dpToPx
import com.example.bullseye.values.StringProvider

class MultiPlayerMenuView(context: Context) : ConstraintLayout(context) {

    private val centerView = LinearLayout(context).apply {
        id = generateViewId()
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
    }
    private val plusButton = MenuIconButton(context).apply {
        id = generateViewId()
        setIcon(
            ContextCompat.getDrawable(
                context,
                R.drawable.add_person
            )
        )
        setOnClickListener {
            makeNewPlayer()
        }
    }
    private val roundTextView = TextView(context).apply {
        text = StringProvider.MultiPlayer.round
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val roundNumberPicker = NumberPicker(context).apply {
        minValue = 2
        maxValue = 99
        value = 2
        wrapSelectorWheel = false // Disable wrapping
        descendantFocusability = FOCUS_BLOCK_DESCENDANTS // Disable keyboard input

        selectionDividerHeight = 0
    }
    private val backButton = MenuIconButton(context).apply {
        setIcon(
            ContextCompat.getDrawable(
                context,
                R.drawable.back
            )
        )
        setOnClickListener {
            removeLatestPlayer()
        }
    }
    private val startButton = MenuIconButton(context).apply {
        setIcon(
            ContextCompat.getDrawable(
                context,
                R.drawable.start
            )
        )
        setOnClickListener {
            findNavController().navigate(R.id.gameFragment)
        }
    }

    private val centerViewWidth = Screen.widthPixels / 3
    private var playerCount = 1

    init {
        addView(plusButton, LayoutParams(50.dpToPx, 50.dpToPx))
        addView(centerView, LayoutParams(centerViewWidth, WRAP_CONTENT).apply {
            setMargins(0, 5.dpToPx, 0, 5.dpToPx)
        })

        centerView.apply {
            val firstLine = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                addView(roundTextView)
                addView(roundNumberPicker, LayoutParams((centerViewWidth / 2), 100.dpToPx))
            }
            val secondLine = LinearLayout(context).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER

                val layoutParams =
                    LayoutParams((centerViewWidth / 2) - 10.dpToPx, 50.dpToPx).apply {
                        setMargins(5.dpToPx, 0, 5.dpToPx, 0)
                    }
                addView(backButton, layoutParams)
                addView(startButton, layoutParams)
            }
            addView(firstLine)
            makeNewPlayer()
            makeNewPlayer()
            addView(secondLine)
        }

        ConstraintSet().apply {
            // Clone
            clone(this@MultiPlayerMenuView)
            // Plus
            connect(plusButton.id, END, PARENT_ID, END)
            connect(plusButton.id, TOP, PARENT_ID, TOP)
            // Center Menu
            centerHorizontally(centerView.id, PARENT_ID)
            centerVertically(centerView.id, PARENT_ID)
            // Apply
            applyTo(this@MultiPlayerMenuView)
        }

    }

    private fun makeNewPlayer() {
        val childCount = centerView.childCount
        if (childCount < 7) {
            val playerTextView = TextView(context).apply {
                text = StringProvider.MultiPlayer.player + playerCount
                setTextColor(Color.BLACK)
                background = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    setColor(Color.WHITE)
                    cornerRadius = 20F
                }
            }
            centerView.addView(
                playerTextView,
                playerCount,
                LayoutParams(centerViewWidth, 20.dpToPx).apply {
                    setMargins(0, 0, 0, 5.dpToPx)
                })
            playerCount++
        }
    }

    private fun removeLatestPlayer() {
        val childCount = centerView.childCount
        if (childCount > 4) {
            centerView.removeViewAt(childCount - 2)
            playerCount--
        }
    }
}