package com.anzid.day_night_mode.manager

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.anzid.day_night_mode.ChangeListener
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.store.DynamicThemeStore
import com.anzid.day_night_mode.theme.Theme

interface DynamicThemeManager {
    var mode: DayNightMode

    fun initModeInflater(activity: AppCompatActivity,
                         factory: LayoutInflater.Factory2? = null)

    fun getDayNightModeStore(): DynamicThemeStore

    fun updateStatusBar(activity: Activity)
    fun updateSelectedThemeAndModeIfNeeded(newTheme: Theme)

    fun addListener(listener: ChangeListener)
    fun removeListener(listener: ChangeListener)
}