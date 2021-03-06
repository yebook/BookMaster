package com.kermitye.baselib.util

import android.content.Context
import android.util.DisplayMetrics


/**
 * Created by kermitye on 2018/10/18 19:15
 */
object BaseUtils {
    fun dp2px(context: Context?, dipValue: Float): Int {
        if (context == null)
            return 0
        val scale = context.getResources().getDisplayMetrics().density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun getScreenWidth(context: Context?): Int {
        if (context == null)
            return 0
        val displayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }
}