package com.anzid.day_night_mode

import com.anzid.day_night_mode.store.DayNightModeStore
import com.anzid.day_night_mode.theme.Theme

sealed class DayNightMode {

    companion object {
        @DayNightModeInt
        internal const val DAY_MODE = 0

        @DayNightModeInt
        private const val NIGHT_MODE = 1

        fun getModeWithSelectedTheme(@DayNightModeInt mode: Int,
                                     store: DayNightModeStore): DayNightMode {
            return when (mode) {
                DAY_MODE -> DayMode(store.getSelectedThemeForDayMode())
                NIGHT_MODE -> NightMode(store.getSelectedThemeForNightMode())
                else -> throw AssertionError("Unsupported")
            }
        }

        fun updateMode(): DayNightMode {
            return when (DayNightModeInitializer.getDayNightMode()) {
                is DayMode -> NightMode(store.getSelectedThemeForNightMode())
                is NightMode -> DayMode(store.getSelectedThemeForDayMode())
            }
        }

        fun updateThemeForMode(): DayNightMode {
            return when (DayNightModeInitializer.getDayNightMode()) {
                is DayMode -> DayMode(store.getSelectedThemeForDayMode())
                is NightMode -> NightMode(store.getSelectedThemeForNightMode())
            }
        }
    }

    abstract val theme: Theme

    fun toModeInt(): @DayNightModeInt Int {
        return when (this) {
            is DayMode -> DAY_MODE
            is NightMode -> NIGHT_MODE
        }
    }
}

data class DayMode(override val theme: Theme) : DayNightMode()

data class NightMode(override val theme: Theme) : DayNightMode()