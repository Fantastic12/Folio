package com.anzid.portfolioapp.themes

import android.graphics.Color
import com.anzid.day_night_mode.theme.Theme

object DarkTheme : Theme {
    override val isNightMode: Boolean
        get() = true

    override val colorPrimary: Int
        get() = Color.BLUE

    override val colorPrimaryDark: Int
        get() = Color.BLUE

    override val colorAccent: Int
        get() = Color.RED

    override val iconTint: Int
        get() = Color.GRAY

    override val backgroundColor: Int
        get() = Color.CYAN

    override val primaryTextColor: Int
        get() = iconTint

    override val secondTextColor: Int
        get() = iconTint

    override val statusBarColor: Int
        get() = colorPrimary
}