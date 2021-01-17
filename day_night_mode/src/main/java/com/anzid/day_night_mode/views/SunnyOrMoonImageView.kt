package com.anzid.day_night_mode.views

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.NightMode
import com.anzid.day_night_mode.R
import com.anzid.day_night_mode.dayNightMode

class SunnyOrMoonImageView @JvmOverloads constructor(context: Context,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        onModeChangedDefault(dayNightMode)
    }

    fun onModeChangedDefault(mode: DayNightMode) {
        setColorFilter(mode.theme.iconTint)

        if (mode is NightMode) setImageResource(R.drawable.ic_sunny)
        else setImageResource(R.drawable.ic_nights_stay_24)
    }

    fun onModeChanged(mode: DayNightMode,
                      @DrawableRes day: Int,
                      @DrawableRes night: Int) {
        setColorFilter(mode.theme.iconTint)

        if (mode is NightMode) setImageResource(day)
        else setImageResource(night)
    }
}