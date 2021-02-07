package com.anzid.dynamic_theme.views.base

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.anzid.dynamic_theme.*
import com.anzid.dynamic_theme.theme.ThemeChangedListener

open class BaseDynamicThemeConstraintLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ThemeChangedListener {

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