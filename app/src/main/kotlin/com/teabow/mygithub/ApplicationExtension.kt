package com.teabow.mygithub

import android.app.Activity
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewManager
import com.google.android.flexbox.FlexboxLayout
import org.jetbrains.anko.custom.ankoView

/**
 * Created by thibaud.bourgeois on 15/01/2017.
 * Application extension.
 */

inline fun Activity.flexLayout(theme: Int = 0) = flexLayout(theme) {}
inline fun Activity.flexLayout(theme: Int = 0, init: FlexboxLayout.() -> Unit) = ankoView({ FlexboxLayout(it) }, theme, init)
inline fun ViewManager.flexLayout(theme: Int = 0) = flexLayout(theme) {}
inline fun ViewManager.flexLayout(theme: Int = 0, init: FlexboxLayout.() -> Unit) = ankoView({ FlexboxLayout(it) }, theme, init)

fun Activity.color(color: Int, theme: Resources.Theme): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        this.resources.getColor(color, theme)
    else
        this.resources.getColor(color)
}

private val defaultInit: Any.() -> Unit = {}
fun <T: View> T.lparams(
        c: android.content.Context?,
        attrs: android.util.AttributeSet?,
        init: FlexboxLayout.LayoutParams.() -> Unit = defaultInit
): T {
    val layoutParams = FlexboxLayout.LayoutParams(c!!, attrs!!)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
}
fun <T: View> T.lparams(
        width: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        height: Int = android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
        init: FlexboxLayout.LayoutParams.() -> Unit = defaultInit
): T {
    val layoutParams = FlexboxLayout.LayoutParams(width, height)
    layoutParams.init()
    this@lparams.layoutParams = layoutParams
    return this
}
