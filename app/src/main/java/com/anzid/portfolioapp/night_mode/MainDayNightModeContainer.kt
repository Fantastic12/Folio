package com.anzid.portfolioapp.night_mode

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainDayNightModeContainer @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ThemeChangedListener {

    init {
        onThemeChanged(ThemeManager.mode)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        ThemeManager.addListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        ThemeManager.addListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ThemeManager.removeListener(this)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setBackgroundColor(
                ContextCompat.getColor(
                        context,
                        mode.theme.colorPrimary
                )
        )
    }
}