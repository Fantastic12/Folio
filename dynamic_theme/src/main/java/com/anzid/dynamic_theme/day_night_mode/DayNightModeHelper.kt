package com.anzid.dynamic_theme.day_night_mode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import com.anzid.dynamic_theme.DynamicThemeInitializer
import com.anzid.dynamic_theme.day_night_mode.DayNightMode.Companion.updateMode
import com.anzid.dynamic_theme.interpolator.Easings
import com.anzid.dynamic_theme.views.sunny_or_moon.SunnyOrMoonImageView
import java.lang.AssertionError
import kotlin.math.hypot

internal class DayNightModeHelper(private val activity: Activity,
                                  private val mainContainer: View,
                                  private val screen: ImageView,
                                  private val nightMode: SunnyOrMoonImageView) {

    internal companion object {
        var DEFAULT_ANIMATION = 400L
    }

    private var animationDuration = DEFAULT_ANIMATION

    internal fun setAnimationDuration(duration: Long) {
        animationDuration = duration
    }

    internal fun initNightModeListeners() {
        nightMode.setOnClickListener {
            updateDayNightMode()
        }
    }

    private fun updateDayNightMode() {
        if (screen.visibility == View.VISIBLE) return

        val w = mainContainer.measuredWidth
        val h = mainContainer.measuredHeight
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        mainContainer.draw(canvas)
        screen.setImageBitmap(bitmap)
        screen.visibility = View.VISIBLE

        with(DynamicThemeInitializer.getDynamicThemeManager()) {
            mode = updateMode()
            updateStatusBar(activity)
        }
        animate(w, h)
    }

    private fun animate(mainContainerWidth: Int, mainContainerHeight: Int) {
        val finalRadius = hypot(mainContainerWidth.toFloat(), mainContainerHeight.toFloat())
        val x = (nightMode.right - nightMode.left) / 2 + nightMode.left
        val y = (nightMode.bottom - nightMode.top) / 2 + nightMode.top

        val anim: Animator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewAnimationUtils.createCircularReveal(screen, x, y, finalRadius, 0f)
        } else {
            throw AssertionError("Unsupported")
        }
        anim.duration = animationDuration
        anim.interpolator = Easings.easeInOutQuad
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                screen.setImageDrawable(null)
                screen.visibility = View.GONE
                nightMode.onModeChangedDefault(DynamicThemeInitializer.getDayNightMode())
            }
        })
        anim.start()
    }
}