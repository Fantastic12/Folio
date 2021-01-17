package com.anzid.portfolioapp.themes

import com.anzid.day_night_mode.theme.DefaultNightModeTheme
import com.anzid.portfolioapp.R

object BlackTheme : DefaultNightModeTheme {
    override val colorPrimary: Int
        get() = R.color.night_colorPrimary

    override val colorPrimaryDark: Int
        get() = R.color.night_colorPrimaryDark

    override val colorAccent: Int
        get() = R.color.night_colorAccent

    override val iconTint: Int
        get() = R.color.night_icon_tint

    override val cardColorBg: Int
        get() = R.color.night_card_bg_color

    override val primaryTextColor: Int
        get() = iconTint

    override val secondTextColor: Int
        get() = iconTint
}