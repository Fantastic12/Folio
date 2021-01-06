package com.anzid.utils.helpers

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Allow to get details about screen
 */

/**
 * Gets device screen size in pixels
 * @return object tht represents screen
 */
fun getScreenSize(context: Context): Screen {
    val display = (context.getSystemService(Activity.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    display.getSize(size)
    return Screen(size.x, size.y)
}

/**
 * This method converts device specific pixels to density independent pixels.
 *
 * @param px      A value in px (pixels) unit. Which we need to convert into db
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent dp equivalent to px value
 */
fun convertPxToDp(context: Context, px: Float): Float {
    return px / context.resources.displayMetrics.density
}

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 *
 * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent px equivalent to dp depending on device density
 */
fun convertDpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}

/**
 * Represents device screen with dimens
 * @param width screen size in pixels
 * @param height screen size in pixels
 */
data class Screen(val width: Int, val height: Int)