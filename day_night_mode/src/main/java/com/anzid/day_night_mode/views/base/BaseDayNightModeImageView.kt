package com.anzid.day_night_mode.views.base

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.anzid.day_night_mode.addThemeListener
import com.anzid.day_night_mode.removeThemeListener
import com.anzid.day_night_mode.theme.ThemeChangedListener

abstract class BaseDayNightModeImageView @JvmOverloads constructor(context: Context,
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