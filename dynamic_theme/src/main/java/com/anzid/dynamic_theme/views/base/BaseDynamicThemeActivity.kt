package com.anzid.dynamic_theme.views.base

import androidx.appcompat.app.AppCompatActivity
import com.anzid.dynamic_theme.addThemeListener
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.removeThemeListener
import com.anzid.dynamic_theme.theme.ThemeChangedListener

abstract class BaseDynamicThemeActivity : AppCompatActivity(), ThemeChangedListener {

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