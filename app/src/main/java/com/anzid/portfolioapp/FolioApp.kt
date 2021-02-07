package com.anzid.portfolioapp

import android.app.Application
import android.preference.PreferenceManager
import com.anzid.dynamic_theme.DayNightMode
import com.anzid.dynamic_theme.DynamicThemeConfiguration
import com.anzid.dynamic_theme.theme.ThemeModel
import com.anzid.portfolioapp.themes.BlackTheme
import com.anzid.portfolioapp.themes.DarkTheme
import com.anzid.portfolioapp.themes.LightTheme
import com.anzid.portfolioapp.themes.WhiteTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FolioApp : Application() {

    companion object {
        lateinit var FOLIO_APP: FolioApp
    }

    override fun onCreate() {
        FOLIO_APP = this
        configureDayModeFeature()
        super.onCreate()
    }


    private fun configureDayModeFeature() {
        DynamicThemeConfiguration.Builder(this)
                .setTheme(ThemeModel(WhiteTheme, isDefaultDayMode = true))
                .setTheme(ThemeModel(BlackTheme, isDefaultNightMode = true))
                .setTheme(ThemeModel(DarkTheme))
                .setTheme(ThemeModel(LightTheme))
                .setOnModeChangeListener { handleChangeMode(it) }
                .configure()
    }

    private fun handleChangeMode(mode: DayNightMode) {
        val theme = when (mode.theme) {
            is WhiteTheme -> "white"
            is BlackTheme -> "black"
            is DarkTheme -> "dark"
            is LightTheme -> "light"
            else -> throw AssertionError()
        }

        PreferenceManager.getDefaultSharedPreferences(this).edit().putString("theme", theme).apply()
    }
}