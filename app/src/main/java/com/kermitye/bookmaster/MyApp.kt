package com.kermitye.bookmaster

import android.app.Application

/**
 * Created by kermitye
 * Date: 2018/9/21 14:14
 * Desc:
 */
class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
        fun getString(resId: Int) = instance.getString(resId)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}