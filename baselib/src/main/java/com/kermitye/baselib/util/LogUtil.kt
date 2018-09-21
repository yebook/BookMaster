package com.kermitye.baselib.util

import android.util.Log

/**
 * Created by kermitye
 * Date: 2018/8/30 14:02
 * Desc:
 */
object LogUtil {
    private var isOpen: Boolean = true
    private val TAG: String = "LogUtil"

    @JvmStatic
    fun d(msg: String, tag: String = TAG) {
        if (isOpen) {
            Log.d(tag, msg)
        }
    }
    @JvmStatic
    fun i(msg: String, tag: String = TAG) {
        if (isOpen) {
            Log.i(tag, msg)
        }
    }
    @JvmStatic
    fun e(msg: String, tag: String = TAG) {
        if (isOpen) {
            Log.e(tag, msg)
        }
    }
}