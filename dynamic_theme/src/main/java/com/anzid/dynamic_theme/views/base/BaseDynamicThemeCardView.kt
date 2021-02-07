package com.anzid.dynamic_theme.views.base

import android.content.Context
import android.util.AttributeSet
import com.anzid.dynamic_theme.addThemeListener
import com.anzid.dynamic_theme.removeThemeListener
import com.anzid.dynamic_theme.theme.ThemeChangedListener
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