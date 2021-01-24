package com.anzid.portfolioapp.themes

import com.anzid.day_night_mode.theme.Theme
import com.anzid.portfolioapp.R

object WhiteTheme : Theme {
    override val colorPrimary: Int
        get() = R.color.colorPrimary

    override val colorPrimaryDark: Int
        get() = R.color.colorPrimaryDark

    override val colorAccent: Int
        get() = R.color.colorAccent

    override val iconTint: Int
        get() = R.color.icon_tint

    override val backgroundColor: Int
        get() = R.color.card_bg_color

    override val primaryTextColor: Int
        get() = iconTint

    override val secondTextColor: Int
        get() = iconTint

    override val statusBarColor: Int
        get() = colorPrimary
}