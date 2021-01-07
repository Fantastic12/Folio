package com.anzid.portfolioapp

import android.content.Context
import android.preference.PreferenceManager

private const val THEME = "pref_theme"

fun getTheme(context: Context) : DayNightMode {
    val ordinal = PreferenceManager.getDefaultSharedPreferences(context).getInt(THEME, DayNightMode.DAY.ordinal)
    return DayNightMode.values()[ordinal]
}

fun setTheme(context: Context, theme: DayNightMode) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(THEME, theme.ordinal).apply()
}