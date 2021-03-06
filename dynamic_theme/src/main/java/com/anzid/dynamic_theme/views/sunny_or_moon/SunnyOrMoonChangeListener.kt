package com.anzid.dynamic_theme.views.sunny_or_moon

import com.anzid.dynamic_theme.ChangeListener
import com.anzid.dynamic_theme.day_night_mode.DayNightMode

interface SunnyOrMoonChangeListener : ChangeListener {
    fun onUpdate(mode: DayNightMode)
}