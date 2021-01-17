package com.anzid.day_night_mode.theme

import com.anzid.day_night_mode.DayNightMode

interface ThemeChangedListener {
    fun onThemeChanged(mode: DayNightMode) = Unit
}