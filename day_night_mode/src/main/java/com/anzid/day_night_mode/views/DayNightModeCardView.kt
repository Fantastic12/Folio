package com.anzid.day_night_mode.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.dayNightMode
import com.anzid.day_night_mode.views.base.BaseDayNightModeCardView

class DayNightModeCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseDayNightModeCardView(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            background.setTint(mode.theme.backgroundColor)
        }
    }
}