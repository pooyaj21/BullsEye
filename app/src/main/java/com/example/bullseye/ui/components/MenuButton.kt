package com.example.bullseye.ui.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.widget.AppCompatButton

class MenuButton(context: Context) : AppCompatButton(context) {

    init {
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor("#B36336"))
            cornerRadius = 20F
        }
        textAlignment = TEXT_ALIGNMENT_CENTER
        setTextColor(Color.WHITE)
    }
}
