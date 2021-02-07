package com.anzid.dynamic_theme.views

import android.content.Context
import android.util.AttributeSet
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.views.base.BaseDynamicThemeConstraintLayout

class MainDynamicThemeContainer @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BaseDynamicThemeConstraintLayout(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setBackgroundColor(mode.theme.colorPrimary)
    }
}