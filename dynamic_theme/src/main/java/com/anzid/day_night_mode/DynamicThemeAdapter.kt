package com.anzid.day_night_mode

import androidx.recyclerview.widget.RecyclerView
import com.anzid.day_night_mode.theme.ThemeChangedListener

abstract class DynamicThemeAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>(), ThemeChangedListener {

    override fun onViewAttachedToWindow(holder: T) {
        super.onViewAttachedToWindow(holder)
        DayNightModeInitializer.getDynamicThemeManager().addListener(this)
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        DayNightModeInitializer.getDynamicThemeManager().removeListener(this)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        notifyDataSetChanged()
    }
}