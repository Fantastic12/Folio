package com.anzid.portfolioapp.night_mode

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.LayoutInflaterCompat
import com.anzid.portfolioapp.FolioApp
import com.anzid.portfolioapp.getTheme
import com.anzid.portfolioapp.setTheme

object ThemeManager {

    private val listeners = mutableSetOf<ThemeChangedListener>()
    var mode = getTheme(FolioApp.FOLIO_APP)
        set(value) {
            field = value
            setTheme(FolioApp.FOLIO_APP, value)
            listeners.forEach { listener -> listener.onThemeChanged(value) }
        }

    @JvmStatic
    fun initModeInflater(activity: Activity, delegate: AppCompatDelegate) {
        LayoutInflaterCompat.setFactory2(
                LayoutInflater.from(activity),
                DayNightModeLayoutInflater(delegate)
        )
        updateStatusBar(activity)
    }

    fun addListener(listener: ThemeChangedListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: ThemeChangedListener) {
        listeners.remove(listener)
    }

    @JvmStatic
    fun updateStatusBar(activity: Activity) {
        if (mode is DayMode) {
            activity.window.statusBarColor = ContextCompat.getColor(activity, android.R.color.white)
        } else {
            activity.window.statusBarColor = ContextCompat.getColor(activity, android.R.color.black)
        }
    }
}