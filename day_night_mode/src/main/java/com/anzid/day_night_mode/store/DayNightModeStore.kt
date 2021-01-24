package com.anzid.day_night_mode.store

import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.theme.Theme

interface DayNightModeStore {
    fun setDayNightMode(mode: DayNightMode)
    fun getDayNightMode(): DayNightMode

    fun getSelectedThemeForDayMode(): Theme
    fun getSelectedThemeForNightMode(): Theme
}