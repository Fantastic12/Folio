package com.anzid.portfolioapp

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class DayNightModeImageView @JvmOverloads constructor(context: Context,
                                                      attrs: AttributeSet? = null,
                                                      defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {


    init {
        setImageRes(ThemeManager.theme)
    }

    fun setImageRes(theme: DayNightMode) {
        setColorFilter(theme.tint)
        if (theme == DayNightMode.NIGHT) {
            setImageResource(R.drawable.ic_sunny)
        } else {
            setImageResource(R.drawable.ic_nights_stay_24)
        }
    }
}