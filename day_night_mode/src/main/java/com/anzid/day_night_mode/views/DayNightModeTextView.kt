package com.anzid.day_night_mode.views

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.dayNightMode
import com.anzid.day_night_mode.views.base.BaseDayNightModeTextView

class DayNightModeTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseDayNightModeTextView(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(dayNightMode)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        setTextColor(
                ContextCompat.getColor(
                        context,
                        mode.theme.primaryTextColor
                )
        )
    }
}