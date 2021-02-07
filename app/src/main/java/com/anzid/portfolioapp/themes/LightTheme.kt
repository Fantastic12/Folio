package com.anzid.portfolioapp.themes

import android.graphics.Color
import com.anzid.portfolioapp.R

object LightTheme : FolioTheme {
    override val isNightMode: Boolean
        get() = false

    override val colorPrimary: Int
        get() = Color.CYAN

    override val colorPrimaryDark: Int
        get() = Color.CYAN

    override val colorAccent: Int
        get() = getColorIntByRes(R.color.colorAccent)

    override val iconTint: Int
        get() = getColorIntByRes(R.color.night_card_bg_color)

    override val backgroundColor: Int
        get() = getColorIntByRes(R.color.card_bg_color)

    override val primaryTextColor: Int
        get() = iconTint

    override val secondTextColor: Int
        get() = iconTint

    override val statusBarColor: Int
        get() = colorPrimary

    override val cvLine: Int
        get() = iconTint
}