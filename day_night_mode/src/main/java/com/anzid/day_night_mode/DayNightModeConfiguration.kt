package com.anzid.day_night_mode

import android.content.Context
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.store.DefaultDayNightModeStore
import com.anzid.day_night_mode.theme.ThemeModel

class DayNightModeConfiguration private constructor(
        private val context: Context,
        private val themes: Array<ThemeModel>,
        private val dayNightModeStore: DayNightModeStore
) {

    class Builder(private val context: Context) {
        private var themes = arrayOf<ThemeModel>()
        private var dayNightModeStore: DayNightModeStore = DefaultDayNightModeStore(context)

        fun setTheme(themeModel: ThemeModel) = apply {
            themeModel.id = themes.size + 1
            themes = themes.plusElement(themeModel)
        }

        fun setStore(store: DayNightModeStore) = apply {
            dayNightModeStore = store
        }

        fun configure() = DayNightModeConfiguration(context, themes, dayNightModeStore).also {
            DayNightModeInitializer.initAppDayNightMode(
                    it.context,
                    it.dayNightModeStore,
                    it.themes
            )
        }
    }
}