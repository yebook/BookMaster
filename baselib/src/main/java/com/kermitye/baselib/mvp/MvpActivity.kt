package com.kermitye.baselib.mvp

import android.os.Bundle
import com.kermitye.baselib.ui.BaseActivity
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast as showToast

/**
 * Created by kermitye
 * Date: 2018/8/28 18:13
 * Desc:
 */
open class MvpActivity<P: BasePresenter<*, *>> : BaseActivity(), IBaseView {

    lateinit var mPresenter: P

    override fun toast(msg: String, isLong: Boolean) {
        if (isLong)
            longToast(msg)
        else
            showToast(msg)
    }
}