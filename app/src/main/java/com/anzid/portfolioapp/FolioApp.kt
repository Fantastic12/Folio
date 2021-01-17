package com.anzid.portfolioapp

import android.app.Application
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
        DayNightModeInitializer.initAppDayNightMode(this,
                theme = arrayOf(
                        ThemeModel(1, WhiteTheme, isDefaultDayMode = true),
                        ThemeModel(2, BlackTheme, isDefaultNightMode = true)
                )
        )
        super.onCreate()
    }
}