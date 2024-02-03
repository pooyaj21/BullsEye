package com.example.bullseye

import android.content.res.Resources

object Screen {

    var widthPixels = 0
        private set
    var heightPixels = 0
        private set

    fun Resources.getScreenSize() {
        val displayMetrics = this.displayMetrics
        widthPixels = displayMetrics.widthPixels
        heightPixels = displayMetrics.heightPixels
    }
}