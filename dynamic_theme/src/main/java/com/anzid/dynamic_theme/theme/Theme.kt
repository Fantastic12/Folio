package com.anzid.dynamic_theme.theme

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.anzid.dynamic_theme.DynamicThemeInitializer

interface Theme {
    val isNightMode: Boolean

    @get:ColorInt
    val colorPrimary: Int

    @get:ColorInt
    val colorPrimaryDark: Int

    @get:ColorInt
    val colorAccent: Int

    @get:ColorInt
    val iconTint: Int

    @get:ColorInt
    val backgroundColor: Int

    @get:ColorInt
    val primaryTextColor: Int

    @get:ColorInt
    val secondTextColor: Int

    @get:ColorInt
    val statusBarColor: Int

    @ColorInt
    fun getColorIntByRes(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(DynamicThemeInitializer.appContext, colorRes)
    }

    @ColorInt
    fun getColorIntByHex(hex: String): Int {
        return Color.parseColor(hex)
    }
}