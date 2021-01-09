package com.anzid.portfolioapp.night_mode

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.anzid.portfolioapp.R

class DayNightModeImageView @JvmOverloads constructor(context: Context,
                                                      attrs: AttributeSet? = null,
                                                      defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        onThemeChanged(ThemeManager.mode)
    }

    fun onThemeChanged(mode: DayNightMode) {
        setColorFilter(mode.theme.iconTint)

        if (mode is NightMode) setImageResource(R.drawable.ic_sunny)
        else setImageResource(R.drawable.ic_nights_stay_24)
    }
}