package com.anzid.day_night_mode

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anzid.day_night_mode.store.DynamicThemeStore

internal val store: DynamicThemeStore
    get() = DayNightModeInitializer.getDayNightModeStore()

internal val View.dayNightMode: DayNightMode
    get() = DayNightModeInitializer.getDayNightMode()


internal fun View.addThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDynamicThemeManager().addListener(listener)
}

internal fun View.removeThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDynamicThemeManager().removeListener(listener)
}

internal val Fragment.dayNightMode: DayNightMode
    get() = DayNightModeInitializer.getDayNightMode()

internal fun Fragment.addThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDynamicThemeManager().addListener(listener)
}

internal fun Fragment.removeThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDynamicThemeManager().removeListener(listener)
}

internal val AppCompatActivity.dayNightMode: DayNightMode
    get() = DayNightModeInitializer.getDayNightMode()

internal fun AppCompatActivity.addThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDynamicThemeManager().addListener(listener)
}

internal fun AppCompatActivity.removeThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDynamicThemeManager().removeListener(listener)
}
