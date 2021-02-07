package com.anzid.dynamic_theme.manager

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.LayoutInflaterCompat
import com.anzid.dynamic_theme.*
import com.anzid.dynamic_theme.day_night_mode.DayMode
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.day_night_mode.DayNightMode.Companion.updateThemeForMode
import com.anzid.dynamic_theme.day_night_mode.NightMode
import com.anzid.dynamic_theme.store.DynamicThemeStore
import com.anzid.dynamic_theme.theme.Theme
import com.anzid.dynamic_theme.theme.ThemeChangedListener
import com.anzid.dynamic_theme.views.sunny_or_moon.SunnyOrMoonChangeListener

class DynamicThemeManagerImpl(private val context: Context,
                              private val store: DynamicThemeStore,
                              private val onModeChange: (DayNightMode) -> Unit) : DynamicThemeManager {

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
                factory ?: DynamicThemeLayoutInflater(activity.delegate)
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