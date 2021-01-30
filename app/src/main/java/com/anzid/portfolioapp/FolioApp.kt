package com.anzid.portfolioapp

import android.app.Application
import android.preference.PreferenceManager
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.DayNightModeConfiguration
import com.anzid.day_night_mode.theme.ThemeModel
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
        DayNightModeConfiguration.Builder(this)
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