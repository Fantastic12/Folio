package com.anzid.day_night_mode.theme

data class ThemeModel(@ThemeId val id: Int,
                      val theme: Theme,
                      val isDefaultDayMode: Boolean = false,
                      val isDefaultNightMode: Boolean = false)