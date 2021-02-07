package com.anzid.dynamic_theme.views

import android.content.Context
import android.util.AttributeSet
import com.anzid.dynamic_theme.DayNightMode
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.theme.ThemeChangedListener
import com.anzid.dynamic_theme.views.base.BaseDynamicThemeImageView

class DynamicThemeImageView @JvmOverloads constructor(context: Context,
                                                      attrs: AttributeSet? = null,
                                                      defStyleAttr: Int = 0
) : BaseDynamicThemeImageView(context, attrs, defStyleAttr), ThemeChangedListener {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setColorFilter(mode.theme.iconTint)
    }
}