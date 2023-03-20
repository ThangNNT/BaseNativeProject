package com.example.nativebaseproject.common.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 */
fun Float.toPixel(context: Context): Float {
    return this * (context.resources
        .displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 * This method converts device specific pixels to density independent pixels.
 */
fun Float.toDp(context: Context): Float {
    return this / (context.resources
        .displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 */
fun Int.toPixel(context: Context): Int {
    return this.toFloat().toPixel(context).toInt()
}

/**
 * This method converts device specific pixels to density independent pixels.
 */
fun Int.toDp(context: Context): Int {
    return this.toFloat().toDp(context).toInt()
}