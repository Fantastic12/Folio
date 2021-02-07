package com.anzid.day_night_mode.views.base

import android.content.Context
import android.util.AttributeSet
import com.anzid.day_night_mode.addThemeListener
import com.anzid.day_night_mode.removeThemeListener
import com.anzid.day_night_mode.theme.ThemeChangedListener
import com.google.android.material.card.MaterialCardView

open class BaseDynamicThemeCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr), ThemeChangedListener {

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