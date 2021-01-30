package com.anzid.portfolioapp.sidemenu

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.anzid.day_night_mode.DayNightMode
import com.anzid.day_night_mode.DayNightModeInitializer.getDayNightMode
import com.anzid.day_night_mode.views.base.BaseDayNightModeImageView
import com.anzid.portfolioapp.R

class MenuImageView @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttr: Int = 0
) : BaseDayNightModeImageView(context, attrs, defStyleAttr) {

    private var isSelectedStatus = false

    fun setSelectedStatus(status: Boolean) {
        isSelectedStatus = status
        onThemeChanged(getDayNightMode())
    }

    override fun onThemeChanged(mode: DayNightMode) {
        if (isSelectedStatus) {
            setColorFilter(ContextCompat.getColor(context, R.color.nav_selected_color))
        } else {
            setColorFilter(getDayNightMode().theme.iconTint)
        }
    }
}