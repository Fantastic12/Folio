package com.anzid.day_night_mode.store

import android.content.Context
import android.preference.PreferenceManager
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.DayNightModeInitializer
import com.anzid.day_night_mode.DayNightModeInitializer.themes
import com.anzid.day_night_mode.theme.ThemeModel
import com.anzid.day_night_mode.theme.Theme

class DefaultDayNightModeStore(private val context: Context) : DayNightModeStore {

    private val prefMode = "pref_mode"
    private val selectedDayTheme = "pref_selected_theme_for_day"
    private val selectedNightTheme = "pref_selected_theme_for_night"

    override fun setDayNightMode(mode: DayNightMode) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(prefMode, mode.toModeInt()).apply()
    }

    override fun getDayNightMode(): DayNightMode {
        val ordinal = PreferenceManager.getDefaultSharedPreferences(context).getInt(prefMode, DayNightMode.DAY_MODE)
        return DayNightMode.getModeWithSelectedTheme(ordinal, this)
    }

    override fun setSelectedThemeForDayMode(model: ThemeModel) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(selectedDayTheme, model.id).apply()
    }

    override fun setSelectedThemeForNightMode(model: ThemeModel) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(selectedNightTheme, model.id).apply()
    }

    override fun getSelectedThemeForDayMode(): Theme {
        val id = PreferenceManager.getDefaultSharedPreferences(context).getInt(selectedDayTheme, DayNightModeInitializer.getDefaultDayModeThemeId())

        themes.forEach {
            if (it.id == id) return it.theme
        }

        throw AssertionError("not find theme in $themes")
    }

    override fun getSelectedThemeForNightMode(): Theme {
        val id = PreferenceManager.getDefaultSharedPreferences(context).getInt(selectedNightTheme, DayNightModeInitializer.getDefaultNightModeThemeId())

        themes.forEach {
            if (it.id == id) return it.theme
        }

        throw AssertionError("not find theme in $themes")
    }

}