package com.kermitye.baselib.mvp

import android.os.Bundle
import android.view.View
import com.kermitye.baselib.ui.BaseFragment
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast

/**
 * Created by kermitye
 * Date: 2018/8/28 18:13
 * Desc:
 */
abstract class MvpFragment<P : BasePresenter<*, *>> : BaseFragment(), IBaseView {

    lateinit var mPresenter: P

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindMV()
    }

    fun bindMV() {
        mPresenter = initPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachMV()
    }

    abstract fun initPresenter(): P
    abstract fun attachMV()

    override fun toast(msg: String, isLong: Boolean) {
        if (isLong)
            activity?.longToast(msg)
        else
            activity?.toast(msg)
    }
}