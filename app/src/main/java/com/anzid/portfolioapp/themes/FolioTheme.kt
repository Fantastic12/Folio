package com.anzid.portfolioapp.themes

import androidx.annotation.ColorInt
import com.anzid.dynamic_theme.theme.Theme
import com.anzid.portfolioapp.R

interface FolioTheme : Theme {

    @get:ColorInt
    val cvLine: Int
        get() = getColorIntByRes(R.color.cv_line_color)
}