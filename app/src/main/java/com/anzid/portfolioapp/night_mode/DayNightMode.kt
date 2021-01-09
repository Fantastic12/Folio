package com.anzid.portfolioapp.night_mode

sealed class DayNightMode {

    companion object {
        @DayNightModeInt
        const val DAY_WHITE_THEME = 0

        @DayNightModeInt
        const val DAY_LIGHT_THEME = 1

        @DayNightModeInt
        const val NIGHT_BLACK_THEME = 2

        @DayNightModeInt
        const val NIGHT_DARK_THEME = 3

        fun getModeWithSelectedTheme(@DayNightModeInt mode: Int): DayNightMode {
            return when (mode) {
                DAY_WHITE_THEME -> DayMode(WhiteTheme)
                DAY_LIGHT_THEME -> throw AssertionError("Unsupported")
                NIGHT_BLACK_THEME -> NightMode(BlackTheme)
                NIGHT_DARK_THEME -> throw AssertionError("Unsupported")
                else -> throw AssertionError("Unsupported")
            }
        }

        fun updateMode(): DayNightMode {
            return when (ThemeManager.mode) {
                is DayMode -> NightMode(BlackTheme)
                is NightMode -> DayMode(WhiteTheme)
            }
        }
    }

    abstract val theme: Theme

    fun toModeInt(): @DayNightModeInt Int {
        return when (this) {
            is DayMode -> {
                if (theme is WhiteTheme) DAY_WHITE_THEME
                else throw AssertionError("Unsupported")
            }
            is NightMode -> {
                if (theme is BlackTheme) NIGHT_BLACK_THEME
                else throw AssertionError("Unsupported")
            }
        }
    }
}

data class DayMode(override val theme: Theme) : DayNightMode()

data class NightMode(override val theme: Theme) : DayNightMode()