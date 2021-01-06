package com.anzid.portfolioapp

import android.content.Context
import android.preference.PreferenceManager

private const val THEME = "pref_theme"

fun getTheme(context: Context) : Theme {
    val ordinal = PreferenceManager.getDefaultSharedPreferences(context).getInt(THEME, Theme.DAY.ordinal)
    return Theme.values()[ordinal]
}

fun setTheme(context: Context, theme: Theme) {
    PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(THEME, theme.ordinal).apply()
}