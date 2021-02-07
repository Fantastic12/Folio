package com.anzid.day_night_mode

import android.content.Context
import com.anzid.day_night_mode.store.DynamicThemeStore
import com.anzid.day_night_mode.store.DefaultDynamicThemeStore
import com.anzid.day_night_mode.theme.ThemeModel

class DynamicThemeConfiguration private constructor(
        private val context: Context,
        private val themes: Array<ThemeModel>,
        private val dynamicThemeStore: DynamicThemeStore,
        private val onModeChange: (DayNightMode) -> Unit
) {

    class Builder(private val context: Context) {
        private var themes = arrayOf<ThemeModel>()
        private var dynamicThemeStore: DynamicThemeStore = DefaultDynamicThemeStore(context)
        private var onModeChange: (DayNightMode) -> Unit = {}

        fun setTheme(themeModel: ThemeModel) = apply {
            themeModel.incrementId(themes)
            themes = themes.plusElement(themeModel)
        }

        fun setStore(store: DynamicThemeStore) = apply {
            dynamicThemeStore = store
        }

        fun setOnModeChangeListener(modeChange: (DayNightMode) -> Unit) = apply {
            onModeChange = modeChange
        }

        fun configure() = DynamicThemeConfiguration(
                context,
                themes,
                dynamicThemeStore,
                onModeChange
        ).also {
            DayNightModeInitializer.initAppDayNightMode(
                    it.context,
                    it.dynamicThemeStore,
                    it.themes,
                    it.onModeChange
            )
        }
    }
}