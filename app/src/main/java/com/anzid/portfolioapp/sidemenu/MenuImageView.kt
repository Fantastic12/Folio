package com.anzid.portfolioapp.sidemenu

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.DynamicThemeInitializer.getDayNightMode
import com.anzid.dynamic_theme.views.base.BaseDynamicThemeImageView
import com.anzid.portfolioapp.R

class MenuImageView @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttr: Int = 0
) : BaseDynamicThemeImageView(context, attrs, defStyleAttr) {

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