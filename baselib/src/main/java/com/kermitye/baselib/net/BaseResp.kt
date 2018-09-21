package com.kermitye.baselib.net

/**
 * Created by kermitye
 * Date: 2018/8/30 13:59
 * Desc:
 */
data class BaseResp<out T>(val success: Boolean, val code: Int, val msg: String, val data: T?)