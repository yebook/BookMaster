package com.kermitye.baselib.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import com.kermitye.baselib.widget.WaitDialog
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by kermitye
 * Date: 2018/8/28 17:42
 * Desc:
 */
open class BaseFragment : SupportFragment() {

    var mWaitDialog: WaitDialog? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { mWaitDialog = WaitDialog(it) }
    }

    fun showWait(msg: String = "") = mWaitDialog?.show(msg)

    fun hideWait() = mWaitDialog?.dismiss()
}