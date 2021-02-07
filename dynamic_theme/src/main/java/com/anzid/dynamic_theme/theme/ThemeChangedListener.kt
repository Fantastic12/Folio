package com.anzid.dynamic_theme.theme

import com.anzid.dynamic_theme.ChangeListener
import com.anzid.dynamic_theme.DayNightMode

interface ThemeChangedListener : ChangeListener {
    fun onThemeChanged(mode: DayNightMode) = Unit
}