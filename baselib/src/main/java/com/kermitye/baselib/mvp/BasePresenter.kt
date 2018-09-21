package com.kermitye.baselib.mvp

/**
 * Created by kermitye
 * Date: 2018/8/28 18:12
 * Desc:
 */
abstract class BasePresenter<M: IBaseModel, V: IBaseView> {
    var mModel: M? = null
    var mView: V? = null


    fun atttachMV(v: V) {
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
}

