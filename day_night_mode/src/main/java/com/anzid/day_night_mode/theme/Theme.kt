package com.anzid.day_night_mode.theme

import androidx.annotation.ColorRes

interface Theme {
    @get:ColorRes
    val colorPrimary: Int

    @get:ColorRes
    val colorPrimaryDark: Int

    @get:ColorRes
    val colorAccent: Int

    @get:ColorRes
    val iconTint: Int

    @get:ColorRes
    val backgroundColor: Int

    @get:ColorRes
    val primaryTextColor: Int

    @get:ColorRes
    val secondTextColor: Int

    @get:ColorRes
    val statusBarColor: Int
}