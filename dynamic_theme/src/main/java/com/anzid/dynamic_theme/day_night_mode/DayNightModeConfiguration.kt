package com.anzid.dynamic_theme.day_night_mode

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.anzid.dynamic_theme.views.sunny_or_moon.SunnyOrMoonImageView

class DayNightModeConfiguration private constructor(
        private val activity: AppCompatActivity,
        private val screenPlaceholder: ImageView,
        private val animationDuration: Long,
        private val nightMode: SunnyOrMoonImageView
) {

    class Builder(private val activity: AppCompatActivity) {
        private lateinit var screenPlaceholder: ImageView
        private lateinit var nightMode: SunnyOrMoonImageView
        private lateinit var mainContainer: View
        private var animationDuration = DayNightModeHelper.DEFAULT_ANIMATION

        fun setScreenPlaceholder(screen: ImageView) = apply {
            screenPlaceholder = screen
        }

        fun setMainContainer(container: View) = apply {
            mainContainer = container
        }

        fun setAnimationDuration(duration: Long) = apply {
            animationDuration = duration
        }

        fun setSunnyOrMoonImageView(sunnyOrMoon: SunnyOrMoonImageView) = apply {
            nightMode = sunnyOrMoon
        }

        fun configure() = DayNightModeConfiguration(
                activity,
                screenPlaceholder,
                animationDuration,
                nightMode
        ).also {
            with(
                    DayNightModeHelper(
                            activity,
                            mainContainer,
                            screenPlaceholder,
                            nightMode
                    )
            ) {
                initNightModeListeners()
                setAnimationDuration(animationDuration)
            }
        }
    }
}