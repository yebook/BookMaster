package com.kermitye.baselib.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.kermitye.baselib.widget.WaitDialog

/**
 * Created by kermitye
 * Date: 2018/8/28 17:42
 * Desc:
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var mWaitDialog: WaitDialog
    var mImmersionBar : ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWaitDialog = WaitDialog(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mImmersionBar != null)
            mImmersionBar?.destroy();
    }

    protected fun setView(resId: Int) {
        setContentView(resId)
        initImmersionBar()
    }

    open protected fun initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.init()
    }

    fun showWait(msg: String = "") = mWaitDialog.show(msg)
    fun hideWait() = mWaitDialog.dismiss()
}