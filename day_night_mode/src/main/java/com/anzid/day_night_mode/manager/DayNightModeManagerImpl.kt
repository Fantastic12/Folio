package com.anzid.day_night_mode.manager

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.LayoutInflaterCompat
import com.anzid.day_night_mode.DayMode
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.DayNightModeLayoutInflater
import com.anzid.day_night_mode.NightMode
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.theme.Theme
import com.anzid.day_night_mode.theme.ThemeChangedListener

class DayNightModeManagerImpl(private val context: Context,
                              private val store: DayNightModeStore,
                              private val onModeChange: (DayNightMode) -> Unit) : DayNightModeManager {

    private val listeners = mutableSetOf<ThemeChangedListener>()

    override var mode = store.getDayNightMode()
        set(value) {
            field = value
            store.setDayNightMode(value)
            onModeChange.invoke(value)
            listeners.forEach { listener -> listener.onThemeChanged(value) }
        }

    override fun initModeInflater(activity: AppCompatActivity,
                                  factory: LayoutInflater.Factory2?) {
        LayoutInflaterCompat.setFactory2(
                LayoutInflater.from(activity),
                factory ?: DayNightModeLayoutInflater(activity.delegate)
        )
        updateStatusBar(activity)
    }

    override fun getDayNightModeStore() = store

    override fun addListener(listener: ThemeChangedListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: ThemeChangedListener) {
        listeners.remove(listener)
    }

    override fun updateStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = mode.theme.statusBarColor
        }
    }

    override fun updateSelectedThemeAndModeIfNeeded(newTheme: Theme) {
        if (newTheme.isNightMode) {
            store.setSelectedThemeForNightMode(newTheme)
            if (mode is DayMode) mode = DayNightMode.updateMode()
        } else {
            store.setSelectedThemeForDayMode(newTheme)
            if (mode is NightMode) mode = DayNightMode.updateMode()
        }
    }
}