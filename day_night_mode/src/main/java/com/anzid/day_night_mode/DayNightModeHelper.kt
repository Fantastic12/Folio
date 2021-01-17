package com.anzid.day_night_mode

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.anzid.day_night_mode.DayNightMode.Companion.updateMode
import com.anzid.day_night_mode.interpolator.Easings
import com.anzid.day_night_mode.views.SunnyOrMoonImageView
import kotlin.math.hypot

class DayNightModeHelper(private val activity: Activity,
                         private val mainContainer: View,
                         private val screen: ImageView,
                         private val nightMode: SunnyOrMoonImageView) {

    companion object {
        private const val ANIMATION_DURATION = 1000L
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun updateDayNightMode() {
        if (screen.visibility == View.VISIBLE) return

        val w = mainContainer.measuredWidth
        val h = mainContainer.measuredHeight
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        mainContainer.draw(canvas)
        screen.setImageBitmap(bitmap)
        screen.visibility = View.VISIBLE

        with(DayNightModeInitializer.getDayNightModeManager()) {
            mode = updateMode()
            updateStatusBar(activity)
        }
        animate(w, h)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animate(mainContainerWidth: Int, mainContainerHeight: Int) {
        val finalRadius = hypot(mainContainerWidth.toFloat(), mainContainerHeight.toFloat())
        val x = (nightMode.right - nightMode.left) / 2 + nightMode.left
        val y = (nightMode.bottom - nightMode.top) / 2 + nightMode.top

        val anim: Animator = ViewAnimationUtils.createCircularReveal(screen, x, y, finalRadius, 0f)
        anim.duration = ANIMATION_DURATION
        anim.interpolator = Easings.easeInOutQuad
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                screen.setImageDrawable(null)
                screen.visibility = View.GONE
                nightMode.onModeChangedDefault(DayNightModeInitializer.getDayNightMode())
            }
        })
        anim.start()
    }
}