package com.anzid.day_night_mode.manager

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import com.anzid.day_night_mode.*
import com.anzid.day_night_mode.DayNightMode.Companion.updateThemeForMode
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.theme.Theme
import com.anzid.day_night_mode.theme.ThemeChangedListener
import com.anzid.day_night_mode.views.sunny_or_moon.SunnyOrMoonChangeListener

class DayNightModeManagerImpl(private val context: Context,
                              private val store: DayNightModeStore,
                              private val onModeChange: (DayNightMode) -> Unit) : DayNightModeManager {

    private val listeners = mutableSetOf<ThemeChangedListener>()
    private val sunnyMoonListeners = mutableSetOf<SunnyOrMoonChangeListener>()

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

    override fun addListener(listener: ChangeListener) {
        if (listener is ThemeChangedListener) {
            listeners.add(listener)
            return
        }
        if (listener is SunnyOrMoonChangeListener) {
            sunnyMoonListeners.add(listener)
            return
        }
    }

    override fun removeListener(listener: ChangeListener) {
        if (listener is ThemeChangedListener) {
            listeners.remove(listener)
            return
        }
        if (listener is SunnyOrMoonChangeListener) {
            sunnyMoonListeners.remove(listener)
            return
        }
    }

    override fun updateStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = mode.theme.statusBarColor
        }
    }

    override fun updateSelectedThemeAndModeIfNeeded(newTheme: Theme) {
        when (newTheme.isNightMode) {
            true -> {
                store.setSelectedThemeForNightMode(newTheme)
                mode = if (mode is DayMode) {
                    sunnyMoonListeners.forEach { it.onUpdate(NightMode(newTheme)) }
                    DayNightMode.updateMode()
                }
                else updateThemeForMode()
            }
            false -> {
                store.setSelectedThemeForDayMode(newTheme)
                mode = if (mode is NightMode) {
                    sunnyMoonListeners.forEach { it.onUpdate(DayMode(newTheme)) }
                    DayNightMode.updateMode()
                }
                else updateThemeForMode()
            }
        }
    }
}