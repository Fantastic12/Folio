package com.anzid.day_night_mode

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.theme.ThemeChangedListener

interface DayNightModeManager {
    var mode: DayNightMode

    fun initModeInflater(activity: AppCompatActivity,
                         factory: LayoutInflater.Factory2? = null)

    fun getDayNightModeStore(): DayNightModeStore

    fun updateStatusBar(activity: Activity)

    fun addListener(listener: ThemeChangedListener)
    fun removeListener(listener: ThemeChangedListener)
}