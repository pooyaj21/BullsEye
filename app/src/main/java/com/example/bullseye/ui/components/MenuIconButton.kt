package com.example.bullseye.ui.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.bullseye.util.dpToPx

class MenuIconButton(context: Context) : LinearLayout(context) {

    private val icon = ImageView(context)

    init {
        background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor("#B36336"))
            cornerRadius = 20F
        }
        gravity = Gravity.CENTER
        orientation = HORIZONTAL
        setPadding(10.dpToPx, 10.dpToPx, 10.dpToPx, 10.dpToPx)
        addView(icon, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
    }

    fun setIcon(drawable: Drawable?) {
        icon.setImageDrawable(drawable)
    }
}
