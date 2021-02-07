package com.anzid.dynamic_theme

import android.content.Context
import android.util.Log
import com.anzid.dynamic_theme.manager.DynamicThemeManager
import com.anzid.dynamic_theme.manager.DynamicThemeManagerImpl
import com.anzid.dynamic_theme.store.DynamicThemeStore
import com.anzid.dynamic_theme.store.DefaultDynamicThemeStore
import com.anzid.dynamic_theme.theme.Theme
import com.anzid.dynamic_theme.theme.ThemeId
import com.anzid.dynamic_theme.theme.ThemeModel

object DayNightModeInitializer {

    internal lateinit var appContext: Context
    private lateinit var dynamicThemeManager: DynamicThemeManager

    internal val themes: MutableSet<ThemeModel> = mutableSetOf()

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    fun <T : Theme> getSelectedTheme() = getDynamicThemeManager().mode.theme as T

    @JvmStatic
    fun getDayNightMode() = getDynamicThemeManager().mode

    @JvmStatic
    fun getDayNightModeStore() = getDynamicThemeManager().getDayNightModeStore()

    @JvmStatic
    fun getDynamicThemeManager(): DynamicThemeManager {
        if (::dynamicThemeManager.isInitialized.not()) {
            if (::appContext.isInitialized.not()) throw AssertionError("initAppDayNightMode() was not called")
            dynamicThemeManager = DynamicThemeManagerImpl(
                    appContext,
                    DefaultDynamicThemeStore(appContext)
            ) {}
            Log.e(DayNightModeInitializer.toString(), "init dayNightModeManager by default")
        }

        return dynamicThemeManager
    }

    internal fun initAppDayNightMode(context: Context,
                                     store: DynamicThemeStore,
                                     theme: Array<ThemeModel>,
                                     onModeChange: (DayNightMode) -> Unit) {
        appContext = context
        themes.addAll(theme)
        initDayNightModeManager(store, onModeChange)
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

    private fun initDayNightModeManager(store: DynamicThemeStore?, onModeChange: (DayNightMode) -> Unit) {
        if (::dynamicThemeManager.isInitialized.not()) {
            if (::appContext.isInitialized.not()) throw AssertionError("initAppDayNightMode() was not called")
            dynamicThemeManager = DynamicThemeManagerImpl(
                    appContext,
                    store ?: DefaultDynamicThemeStore(appContext),
                    onModeChange
            )
        }
    }
}