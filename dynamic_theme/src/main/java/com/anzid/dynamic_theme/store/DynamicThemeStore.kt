package com.anzid.dynamic_theme.store

import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.theme.Theme

interface DynamicThemeStore {
    fun setDayNightMode(mode: DayNightMode)
    fun getDayNightMode(): DayNightMode

    fun setSelectedThemeForDayMode(theme: Theme)
    fun setSelectedThemeForNightMode(theme: Theme)

    fun getSelectedThemeForDayMode(): Theme
    fun getSelectedThemeForNightMode(): Theme
}