package com.anzid.dynamic_theme.views.base

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.anzid.dynamic_theme.addThemeListener
import com.anzid.dynamic_theme.removeThemeListener
import com.anzid.dynamic_theme.theme.ThemeChangedListener

abstract class BaseDynamicThemeImageView @JvmOverloads constructor(context: Context,
                                                                   attrs: AttributeSet? = null,
                                                                   defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), ThemeChangedListener {

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