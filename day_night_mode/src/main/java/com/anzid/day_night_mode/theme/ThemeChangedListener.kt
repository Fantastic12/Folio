package com.anzid.day_night_mode.theme

import com.anzid.day_night_mode.ChangeListener
import com.anzid.day_night_mode.DayNightMode

interface ThemeChangedListener : ChangeListener {
    fun onThemeChanged(mode: DayNightMode) = Unit
}