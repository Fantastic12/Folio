package com.anzid.day_night_mode.views

import android.content.Context
import android.util.AttributeSet
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.dayNightMode
import com.anzid.day_night_mode.views.base.BaseDayNightModeConstraintLayout

class DayNightModeConstraintLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseDayNightModeConstraintLayout(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setBackgroundColor(mode.theme.backgroundColor)
    }
}