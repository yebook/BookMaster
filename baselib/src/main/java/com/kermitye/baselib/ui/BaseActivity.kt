package com.kermitye.baselib.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide.init
import com.gyf.barlibrary.ImmersionBar
import com.kermitye.baselib.widget.WaitDialog
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by kermitye
 * Date: 2018/8/28 17:42
 * Desc:
 */
open class BaseActivity : SupportActivity() {

    lateinit var mWaitDialog: WaitDialog
    var mImmersionBar: ImmersionBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWaitDialog = WaitDialog(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mImmersionBar != null)
            mImmersionBar?.destroy()
    }

    protected fun setView(resId: Int, topView: Int = -1) {
        setContentView(resId)
        mImmersionBar = ImmersionBar.with(this)
        if (topView != -1) {
            mImmersionBar?.statusBarView(topView)?.init()
        } else {
            mImmersionBar?.init()
        }
    }


    open protected fun initImmersionBar(topView: View? = null) {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar?.init()
        if (topView != null) {
            mImmersionBar?.let { it.statusBarView(topView).init() }
        }
    }

    fun showWait(msg: String = "") = mWaitDialog.show(msg)
    fun hideWait() = mWaitDialog.dismiss()

    fun hindKeyBoard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //得到InputMethodManager的实例
        if (imm.isActive) {//如果开启
            // 强制隐藏软键盘
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            //            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);//关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
        }
    }
}