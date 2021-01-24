package com.anzid.day_night_mode

import android.view.View
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.theme.ThemeChangedListener

internal val store: DayNightModeStore
    get() = DayNightModeInitializer.getDayNightModeStore()

internal val View.dayNightMode: DayNightMode
    get() = DayNightModeInitializer.getDayNightMode()

internal fun View.addThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDayNightModeManager().addListener(listener)
}

internal fun View.removeThemeListener(listener: ChangeListener) {
    DayNightModeInitializer.getDayNightModeManager().removeListener(listener)
}
