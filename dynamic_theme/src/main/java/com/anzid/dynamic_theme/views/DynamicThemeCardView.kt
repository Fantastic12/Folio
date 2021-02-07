package com.anzid.dynamic_theme.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.views.base.BaseDynamicThemeCardView

class DynamicThemeCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseDynamicThemeCardView(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            background.setTint(mode.theme.backgroundColor)
        }
    }
}