package com.anzid.day_night_mode.theme

data class ThemeModel(val theme: Theme,
                      val isDefaultDayMode: Boolean = false,
                      val isDefaultNightMode: Boolean = false) {

    @ThemeId
    internal var id: Int = 0
}