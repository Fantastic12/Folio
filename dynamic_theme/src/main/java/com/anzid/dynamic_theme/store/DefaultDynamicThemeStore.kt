package com.anzid.dynamic_theme.store

import android.content.Context
import android.preference.PreferenceManager
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.DynamicThemeInitializer
import com.anzid.dynamic_theme.DynamicThemeInitializer.themes
import com.anzid.dynamic_theme.theme.Theme

internal class DefaultDynamicThemeStore(private val context: Context) : DynamicThemeStore {

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

    override fun setSelectedThemeForDayMode(theme: Theme) {
        val themeModel = themes.find { it.theme == theme }
                ?: throw AssertionError("not find theme in DayNightModeConfiguration")
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(selectedDayTheme, themeModel.id).apply()
    }

    override fun setSelectedThemeForNightMode(theme: Theme) {
        val themeModel = themes.find { it.theme == theme }
                ?: throw AssertionError("not find theme in DayNightModeConfiguration")
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(selectedNightTheme, themeModel.id).apply()
    }

    override fun getSelectedThemeForDayMode(): Theme {
        val id = PreferenceManager.getDefaultSharedPreferences(context).getInt(selectedDayTheme, DynamicThemeInitializer.getDefaultDayModeThemeId())

        themes.forEach {
            if (it.id == id) return it.theme
        }

        throw AssertionError("not find theme in $themes")
    }

    override fun getSelectedThemeForNightMode(): Theme {
        val id = PreferenceManager.getDefaultSharedPreferences(context).getInt(selectedNightTheme, DynamicThemeInitializer.getDefaultNightModeThemeId())

        themes.forEach {
            if (it.id == id) return it.theme
        }

        throw AssertionError("not find theme in $themes")
    }

}