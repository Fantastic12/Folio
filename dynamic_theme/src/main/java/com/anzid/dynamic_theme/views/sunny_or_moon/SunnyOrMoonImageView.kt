package com.anzid.dynamic_theme.views.sunny_or_moon

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.anzid.dynamic_theme.*
import com.anzid.dynamic_theme.dayNightMode
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.day_night_mode.NightMode

class SunnyOrMoonImageView @JvmOverloads constructor(context: Context,
                                                     attrs: AttributeSet? = null,
                                                     defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), SunnyOrMoonChangeListener {

    init {
        onModeChangedDefault(dayNightMode)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addThemeListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addThemeListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeThemeListener(this)
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

    override fun onUpdate(mode: DayNightMode) {
        onModeChangedDefault(mode)
    }
}