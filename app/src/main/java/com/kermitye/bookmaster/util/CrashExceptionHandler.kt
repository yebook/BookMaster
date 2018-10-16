package com.kermitye.bookmaster.util

import android.os.Environment
import android.util.Log
import com.kermitye.baselib.util.LogUtil
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.experimental.coroutineContext

/**
 * Created by kermitye
 * Date: 2018/8/10 17:05
 * Desc:
 */
class CrashExceptionHandler : Thread.UncaughtExceptionHandler {
    private val TAG: String = "CrashExceptionHandler"

    companion object {
        val instance by lazy { CrashExceptionHandler() }
    }

    override fun uncaughtException(td: Thread, tb: Throwable) {
//        writeToSDCard(td, tb)
        if (td.id == 1L) {// UI异常
            LogUtil.e("UI异常====${tb.message}", TAG)
            tb.printStackTrace()
        } else {
            LogUtil.e("其他异常====${tb.message}", TAG)
            tb.printStackTrace()
        }
        System.exit(1)
    }

    fun init() {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    fun writeToSDCard(td: Thread, tb: Throwable) {
        val sdCardExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        if (sdCardExist) {
            val logFile = File(Environment.getExternalStorageDirectory()
                    .path + "/crashlog/bestNameRobot/")
            if (!logFile.exists()) {
                if (!logFile.mkdirs()) {
                    println("create crash file fail")
                }
            }
            val logPath = (Environment.getExternalStorageDirectory()
                    .path
                    + "/crashlog/bestNameRobot/"
                    + SimpleDateFormat("yyMMddHHmmss", Locale.getDefault())
                    .format(Date()) + ".txt")
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(logPath)
                val ps = PrintStream(fos)
                tb.printStackTrace(ps)
                ps.flush()
                ps.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                if (fos != null) {
                    try {
                        fos.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}