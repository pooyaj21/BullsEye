package com.example.bullseye.ui.game

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.bullseye.R
import com.example.bullseye.Screen
import com.example.bullseye.model.GameType
import com.example.bullseye.util.dpToPx
import com.example.bullseye.values.StringProvider
import kotlin.random.Random

@SuppressLint("ViewConstructor")
class GameView(context: Context, private val gameType: GameType?) : LinearLayout(context) {

    private val promptTextView = TextView(context).apply {
        text = StringProvider.Game.prompt
        textAlignment = TEXT_ALIGNMENT_CENTER
    }
    private val slider = SeekBar(context).apply {
        max = 100
        progress = 50
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
        setPadding(0,20.dpToPx,0,20.dpToPx)
    }
    private val hitMeButton = Button(context).apply {
        text = StringProvider.Game.button
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 10F
            colors = intArrayOf(Color.parseColor("#f88131"), Color.parseColor("#fdc659"))
            orientation = GradientDrawable.Orientation.TOP_BOTTOM
        }
        setPadding(0,10.dpToPx,0,10.dpToPx)
    }

    private val hintsLine = ConstraintLayout(context).apply {

    }

    private var targetNumber = 0
    private var currentScore = 0

    init {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        targetNumber = Random.nextInt(101)
        promptTextView.text = StringProvider.Game.prompt + targetNumber
        hitMeButton.setOnClickListener {
            Toast.makeText(context, slider.progress.toString(), Toast.LENGTH_SHORT).show()
        }

        addView(promptTextView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(sliderView, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
        addView(hitMeButton, LayoutParams(WRAP_CONTENT, WRAP_CONTENT))
    }

}