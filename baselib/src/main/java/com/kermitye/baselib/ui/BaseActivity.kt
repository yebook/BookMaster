package com.kermitye.baselib.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kermitye.baselib.widget.WaitDialog

/**
 * Created by kermitye
 * Date: 2018/8/28 17:42
 * Desc:
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var mWaitDialog: WaitDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWaitDialog = WaitDialog(this)
    }

    fun showWait(msg: String = "") = mWaitDialog.show(msg)
    fun hideWait() = mWaitDialog.dismiss()
}