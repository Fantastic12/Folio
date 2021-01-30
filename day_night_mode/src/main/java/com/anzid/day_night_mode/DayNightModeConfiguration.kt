package com.anzid.day_night_mode

import android.content.Context
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.store.DefaultDayNightModeStore
import com.anzid.day_night_mode.theme.ThemeModel

class DayNightModeConfiguration private constructor(
        private val context: Context,
        private val themes: Array<ThemeModel>,
        private val dayNightModeStore: DayNightModeStore,
        private val onModeChange: (DayNightMode) -> Unit
) {

    class Builder(private val context: Context) {
        private var themes = arrayOf<ThemeModel>()
        private var dayNightModeStore: DayNightModeStore = DefaultDayNightModeStore(context)
        private var onModeChange: (DayNightMode) -> Unit = {}

        fun setTheme(themeModel: ThemeModel) = apply {
            themeModel.incrementId(themes)
            themes = themes.plusElement(themeModel)
        }

        fun setStore(store: DayNightModeStore) = apply {
            dayNightModeStore = store
        }

        fun setOnModeChangeListener(modeChange: (DayNightMode) -> Unit) = apply {
            onModeChange = modeChange
        }

        fun configure() = DayNightModeConfiguration(
                context,
                themes,
                dayNightModeStore,
                onModeChange
        ).also {
            DayNightModeInitializer.initAppDayNightMode(
                    it.context,
                    it.dayNightModeStore,
                    it.themes,
                    it.onModeChange
            )
        }
    }
}