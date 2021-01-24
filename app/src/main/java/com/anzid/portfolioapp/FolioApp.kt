package com.anzid.portfolioapp

import android.app.Application
import com.anzid.day_night_mode.DayNightModeConfiguration
import com.anzid.day_night_mode.DayNightModeInitializer
import com.anzid.day_night_mode.theme.ThemeModel
import com.anzid.portfolioapp.themes.BlackTheme
import com.anzid.portfolioapp.themes.WhiteTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FolioApp : Application() {

    companion object {
        lateinit var FOLIO_APP: FolioApp
    }

    override fun onCreate() {
        FOLIO_APP = this

        DayNightModeConfiguration.Builder(this)
                .setTheme(ThemeModel(WhiteTheme, isDefaultDayMode = true))
                .setTheme(ThemeModel(BlackTheme, isDefaultNightMode = true))
                .configure()

        super.onCreate()
    }
}