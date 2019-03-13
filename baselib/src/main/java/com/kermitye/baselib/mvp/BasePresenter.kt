package com.kermitye.baselib.mvp

import android.arch.lifecycle.DefaultLifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.view.View

/**
 * Created by kermitye
 * Date: 2018/8/28 18:12
 * Desc:
 */
abstract class BasePresenter<M: IBaseModel, V: IBaseView>: DefaultLifecycleObserver {
    var mModel: M? = null
    var mView: V? = null
    var mOwner: LifecycleOwner? = null

    fun attachMV(v: V) {
        this.mModel = getModel()
        this.mView = v
        this.onStart()
    }

    fun detachMV() {
        mView = null
        mModel = null
    }

    abstract fun getModel() : M

    /**
     * 绑定完成之后执行
     */
    abstract fun onStart()


    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        mOwner = owner
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        mOwner = null
    }
}

