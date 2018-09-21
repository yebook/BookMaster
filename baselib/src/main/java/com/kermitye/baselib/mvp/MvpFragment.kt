package com.kermitye.baselib.mvp

import com.kermitye.baselib.ui.BaseFragment
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

/**
 * Created by kermitye
 * Date: 2018/8/28 18:13
 * Desc:
 */
open class MvpFragment : BaseFragment(), IBaseView {

    override fun toast(msg: String, isLong: Boolean) {
        if (isLong)
            activity?.longToast(msg)
        else
            activity?.toast(msg)
    }
}