package com.anzid.dynamic_theme

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.store.DynamicThemeStore

internal val store: DynamicThemeStore
    get() = DynamicThemeInitializer.getDayNightModeStore()

internal val View.dayNightMode: DayNightMode
    get() = DynamicThemeInitializer.getDayNightMode()


internal fun View.addThemeListener(listener: ChangeListener) {
    DynamicThemeInitializer.getDynamicThemeManager().addListener(listener)
}

internal fun View.removeThemeListener(listener: ChangeListener) {
    DynamicThemeInitializer.getDynamicThemeManager().removeListener(listener)
}

internal val Fragment.dayNightMode: DayNightMode
    get() = DynamicThemeInitializer.getDayNightMode()

internal fun Fragment.addThemeListener(listener: ChangeListener) {
    DynamicThemeInitializer.getDynamicThemeManager().addListener(listener)
}

internal fun Fragment.removeThemeListener(listener: ChangeListener) {
    DynamicThemeInitializer.getDynamicThemeManager().removeListener(listener)
}

internal val AppCompatActivity.dayNightMode: DayNightMode
    get() = DynamicThemeInitializer.getDayNightMode()

internal fun AppCompatActivity.addThemeListener(listener: ChangeListener) {
    DynamicThemeInitializer.getDynamicThemeManager().addListener(listener)
}

internal fun AppCompatActivity.removeThemeListener(listener: ChangeListener) {
    DynamicThemeInitializer.getDynamicThemeManager().removeListener(listener)
}
