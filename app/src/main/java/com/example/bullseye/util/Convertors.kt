package com.example.bullseye.util

import android.content.res.Resources

inline val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
