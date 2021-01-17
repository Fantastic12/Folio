package com.anzid.day_night_mode

import android.content.Context
import android.util.Log
import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.store.DefaultDayNightModeStore
import com.anzid.day_night_mode.theme.ThemeId
import com.anzid.day_night_mode.theme.ThemeModel

object DayNightModeInitializer {

    private lateinit var appContext: Context
    private lateinit var dayNightModeManager: DayNightModeManager

    internal val themes: MutableSet<ThemeModel> = mutableSetOf()

    @JvmStatic
    fun initAppDayNightMode(context: Context,
                            store: DayNightModeStore? = null,
                            vararg theme: ThemeModel) {
        appContext = context
        themes.addAll(theme)
        initDayNightModeManager(store)
    }

    @JvmStatic
    fun getDayNightMode() = getDayNightModeManager().mode

    @JvmStatic
    fun getDayNightModeStore() = getDayNightModeManager().getDayNightModeStore()

    @JvmStatic
    fun getDayNightModeManager(): DayNightModeManager {
        if (::dayNightModeManager.isInitialized.not()) {
            if (::appContext.isInitialized.not()) throw AssertionError("initAppThemes was not called")
            dayNightModeManager = DayNightModeManagerImpl(
                    appContext,
                    DefaultDayNightModeStore(appContext)
            )
            Log.e(DayNightModeInitializer.toString(), "init dayNightModeManager by default")
        }

        return dayNightModeManager
    }

    @JvmStatic
    internal fun getDefaultNightModeThemeId(): @ThemeId Int {
        if (themes.isEmpty()) throw AssertionError("themes not init")

        themes.forEach {
            if (it.isDefaultNightMode) {
                return it.id
            }
        }

        throw AssertionError("not find isDefaultNightMode = true")
    }

    @JvmStatic
    internal fun getDefaultDayModeThemeId(): @ThemeId Int {
        if (themes.isEmpty()) throw AssertionError("themes not init")

        themes.forEach {
            if (it.isDefaultDayMode) {
                return it.id
            }
        }

        throw AssertionError("not find isDefaultDayMode = true")
    }

    private fun initDayNightModeManager(store: DayNightModeStore?) {
        if (::dayNightModeManager.isInitialized.not()) {
            if (::appContext.isInitialized.not()) throw AssertionError("initAppThemes was not called")
            dayNightModeManager = DayNightModeManagerImpl(
                    appContext,
                    store ?: DefaultDayNightModeStore(appContext)
            )
        }
    }
}