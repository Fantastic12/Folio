package com.anzid.portfolioapp

enum class DayNightMode(
        val textViewTheme: TextViewTheme,
        val colorPrimary: Int,
        val colorPrimaryDark: Int,
        val tint: Int
) {
    DAY(
            textViewTheme = TextViewTheme(textColor = R.color.icon_tint),
            colorPrimary = R.color.colorPrimary,
            colorPrimaryDark = R.color.colorPrimaryDark,
            tint = R.color.icon_tint
    ),
    NIGHT(textViewTheme = TextViewTheme(textColor = R.color.night_icon_tint),
            colorPrimary = R.color.night_colorPrimary,
            colorPrimaryDark = R.color.night_colorPrimaryDark,
            tint = R.color.night_icon_tint
    );
}