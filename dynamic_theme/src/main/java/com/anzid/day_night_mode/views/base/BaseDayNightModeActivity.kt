package com.anzid.day_night_mode.views.base

import androidx.appcompat.app.AppCompatActivity
import com.anzid.day_night_mode.addThemeListener
import com.anzid.day_night_mode.dayNightMode
import com.anzid.day_night_mode.removeThemeListener
import com.anzid.day_night_mode.theme.ThemeChangedListener

abstract class BaseDayNightModeActivity : AppCompatActivity(), ThemeChangedListener {

    override fun onResume() {
        super.onResume()
        onThemeChanged(dayNightMode)
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