package com.anzid.dynamic_theme.theme

data class ThemeModel(val theme: Theme,
                      val isDefaultDayMode: Boolean = false,
                      val isDefaultNightMode: Boolean = false) {

    @ThemeId
    internal var id: Int = 0

    fun incrementId(themes: Array<ThemeModel>) {
        id = themes.size + 1
    }
}