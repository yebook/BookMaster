package com.kermitye.bookmaster.util

import com.kermitye.baselib.util.LogUtil
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by kermitye on 2018/10/10 11:08
 */
object ToolUtils {
    fun getShortTime(time: String): String {
        LogUtil.e("getShortTime: $time")
        var shortString = ""
        val now = Calendar.getInstance().timeInMillis
        val date = getDateByString(time)
        val delTime = (now - date.time) / 1000

//        if (delTime > 365 * 24 * 60 * 60) {
//            shortString = (delTime / (365 * 24 * 60 * 60)).toInt().toString() + "年前"
//        } else
        if (delTime > 24 * 60 * 60) {
//            shortString = (delTime / (24 * 60 * 60)).toInt().toString() + "天前"
            shortString = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
        } else if (delTime > 60 * 60) {
            shortString = "${(delTime / (60 * 60)).toInt()}小时前"
        } else if (delTime > 60) {
            shortString = (delTime / 60).toInt().toString() + "分前"
        } else if (delTime > 1) {
            shortString = delTime.toString() + "秒前"
        } else {
            shortString = "刚刚"
        }
        return shortString
    }


    fun getDateByString(time: String): Date {
        var date = Date()
        if (time.isNullOrEmpty()) {
            return date
        }
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val format = SimpleDateFormat(pattern)
        try {
            date = format.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
            return date
        }
        return date
    }
}