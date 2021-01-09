package com.anzid.portfolioapp

import android.content.Context
import android.preference.PreferenceManager
import com.anzid.portfolioapp.night_mode.DayNightMode

private const val THEME = "pref_theme"

fun getTheme(context: Context) : DayNightMode {
    val ordinal = PreferenceManager.getDefaultSharedPreferences(context).getInt(THEME, DayNightMode.DAY_WHITE_THEME)
    return DayNightMode.getModeWithSelectedTheme(ordinal)
}

fun setTheme(context: Context, theme: DayNightMode) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(THEME, theme.toModeInt()).apply()
}