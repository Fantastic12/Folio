package com.anzid.day_night_mode.views.sunny_or_moon

import com.anzid.day_night_mode.ChangeListener
import com.anzid.day_night_mode.DayNightMode

interface SunnyOrMoonChangeListener : ChangeListener {
    fun onUpdate(mode: DayNightMode)
}