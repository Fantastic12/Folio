package com.anzid.portfolioapp.themes

import android.graphics.Color

object DarkTheme : FolioTheme {
    override val isNightMode: Boolean
        get() = true

    override val colorPrimary: Int
        get() = getColorIntByHex("#0c2533")

    override val colorPrimaryDark: Int
        get() = getColorIntByHex("#0c2533")

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