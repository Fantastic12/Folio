package com.anzid.day_night_mode.views.base

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.anzid.day_night_mode.*
import com.anzid.day_night_mode.theme.ThemeChangedListener

abstract class BaseDayNightModeTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr), ThemeChangedListener {

    override fun onFinishInflate() {
        super.onFinishInflate()
        addThemeListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addThemeListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeThemeListener(this)
    }
}