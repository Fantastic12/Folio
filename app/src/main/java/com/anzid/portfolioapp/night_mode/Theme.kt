package com.anzid.portfolioapp.night_mode

import androidx.annotation.ColorRes
import com.anzid.portfolioapp.R

sealed class Theme {

    @get:ColorRes
    abstract val colorPrimary: Int

    @get:ColorRes
    abstract val colorPrimaryDark: Int

    @get:ColorRes
    abstract val colorAccent: Int

    @get:ColorRes
    abstract val iconTint: Int

    @get:ColorRes
    abstract val cardColorBg: Int

    @get:ColorRes
    abstract val primaryTextColor: Int

    @get:ColorRes
    abstract val secondTextColor: Int

}

object WhiteTheme : Theme() {
    override val colorPrimary: Int
        get() = R.color.colorPrimary

    override val colorPrimaryDark: Int
        get() = R.color.colorPrimaryDark

    override val colorAccent: Int
        get() = R.color.colorAccent

    override val iconTint: Int
        get() = R.color.icon_tint

    override val cardColorBg: Int
        get() = R.color.card_bg_color

    override val primaryTextColor: Int
        get() = iconTint

    override val secondTextColor: Int
        get() = iconTint
}

object BlackTheme : Theme() {
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