package com.anzid.portfolioapp.night_mode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.LayoutInflaterCompat
import com.anzid.portfolioapp.night_mode.ThemeManager.updateStatusBar
import kotlin.math.hypot

class DayNightModeHelper(private val activity: Activity,
                         private val mainContainer: View,
                         private val screen: ImageView,
                         private val nightMode: DayNightModeImageView) {

    fun updateDayNightMode() {
        if (screen.visibility == View.VISIBLE) return

        val w = mainContainer.measuredWidth
        val h = mainContainer.measuredHeight
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        mainContainer.draw(canvas)
        screen.setImageBitmap(bitmap)
        screen.visibility = View.VISIBLE

        val finalRadius = hypot(w.toFloat(), h.toFloat())
        val x = (nightMode.right - nightMode.left) / 2 + nightMode.left
        val y = (nightMode.bottom - nightMode.top) / 2 + nightMode.top

        ThemeManager.mode = updateMode()
        updateStatusBar(activity)

        val anim: Animator = ViewAnimationUtils.createCircularReveal(screen, x, y, finalRadius, 0f)
        anim.duration = 1000
        anim.interpolator = Easings.easeInOutQuad
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                screen.setImageDrawable(null)
                screen.visibility = View.GONE
                nightMode.onThemeChanged(ThemeManager.mode)
            }
        })
        anim.start()
    }

    private fun updateMode(): DayNightMode {
        return when (ThemeManager.mode) {
            is DayMode -> NightMode(BlackTheme)
            is NightMode -> DayMode(WhiteTheme)
        }
    }
}