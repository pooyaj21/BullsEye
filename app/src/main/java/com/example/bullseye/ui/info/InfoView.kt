package com.example.bullseye.ui.info

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import android.widget.FrameLayout
import com.example.bullseye.R
import com.example.bullseye.util.dpToPx
import com.example.bullseye.values.StringProvider

class InfoView(context: Context) : FrameLayout(context) {

    private val webView = WebView(context).apply {
        loadDataWithBaseURL(null, StringProvider.info.trimIndent(), "text/html", "UTF-8", null)
    }

    init {
        id = R.id.infoFragment
        addView(webView, LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
            setMargins(20.dpToPx, 20.dpToPx, 20.dpToPx, 20.dpToPx)
        })
    }

}