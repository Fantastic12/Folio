package com.anzid.dynamic_theme.manager

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.anzid.dynamic_theme.ChangeListener
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.store.DynamicThemeStore
import com.anzid.dynamic_theme.theme.Theme

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