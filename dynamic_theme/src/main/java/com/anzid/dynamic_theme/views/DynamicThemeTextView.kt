package com.anzid.dynamic_theme.views

import android.content.Context
import android.util.AttributeSet
import com.anzid.dynamic_theme.DayNightMode
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.views.base.BaseDynamicThemeTextView

class DynamicThemeTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseDynamicThemeTextView(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setTextColor(mode.theme.primaryTextColor)
    }
}