package com.anzid.day_night_mode

import android.view.View
import com.anzid.day_night_mode.theme.ThemeChangedListener

val View.dayNightMode : DayNightMode
    get() = DayNightModeInitializer.getDayNightMode()

fun View.addThemeListener(listener: ThemeChangedListener) {
    DayNightModeInitializer.getDayNightModeManager().addListener(listener)
}

fun View.removeThemeListener(listener: ThemeChangedListener) {
    DayNightModeInitializer.getDayNightModeManager().removeListener(listener)
}
