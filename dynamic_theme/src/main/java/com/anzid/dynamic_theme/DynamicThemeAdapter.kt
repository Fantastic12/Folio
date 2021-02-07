package com.anzid.dynamic_theme

import androidx.recyclerview.widget.RecyclerView
import com.anzid.dynamic_theme.day_night_mode.DayNightMode
import com.anzid.dynamic_theme.theme.ThemeChangedListener

abstract class DynamicThemeAdapter<T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>(), ThemeChangedListener {

    override fun onViewAttachedToWindow(holder: T) {
        super.onViewAttachedToWindow(holder)
        DynamicThemeInitializer.getDynamicThemeManager().addListener(this)
    }

    override fun onViewDetachedFromWindow(holder: T) {
        super.onViewDetachedFromWindow(holder)
        DynamicThemeInitializer.getDynamicThemeManager().removeListener(this)
    }

    override fun onThemeChanged(mode: DayNightMode) {
        notifyDataSetChanged()
    }
}