package com.anzid.dynamic_theme.views.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.anzid.dynamic_theme.*
import com.anzid.dynamic_theme.theme.ThemeChangedListener

class BaseDynamicThemeView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), ThemeChangedListener {

    init {
        onThemeChanged(dayNightMode)
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
}