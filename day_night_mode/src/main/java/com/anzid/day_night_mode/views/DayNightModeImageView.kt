package com.anzid.day_night_mode.views

import android.content.Context
import android.util.AttributeSet
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.dayNightMode
import com.anzid.day_night_mode.theme.ThemeChangedListener
import com.anzid.day_night_mode.views.base.BaseDayNightModeImageView

class DayNightModeImageView @JvmOverloads constructor(context: Context,
                                                      attrs: AttributeSet? = null,
                                                      defStyleAttr: Int = 0
) : BaseDayNightModeImageView(context, attrs, defStyleAttr), ThemeChangedListener {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setColorFilter(mode.theme.iconTint)
    }
}