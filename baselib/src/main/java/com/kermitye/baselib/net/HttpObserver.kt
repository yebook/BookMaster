package com.kermitye.baselib.net

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by kermitye
 * Date: 2018/7/18 14:22
 * Desc:
 */
abstract class HttpObserver<T> : Observer<T> {
    /**
     * 标记是否为特殊情况
     */
    private var resultNull: Boolean = true

    override fun onComplete() {
        // 特殊情况：当请求成功，但T == null时会跳过onNext，仍需当成功处理
        if (resultNull)
            onSuccess(null)
    }

    override fun onSubscribe(d: Disposable) {
        // 可在此处加上dialog
//        if (!NetUtils.WIFI_AVAILABLE) {
//            onError(0, "网络异常，请检查网络")
//            d.dispose()
//        }
    }

    override fun onError(e: Throwable) {
        onError(0, e.message)
//        if (e is ApiException) {
//            onError(e.code, e.msg)
//        } else {
//            onError(0, e.message)
//        }
    }

    override fun onNext(t: T) {
        resultNull = false
        onSuccess(t)
    }

    abstract fun onSuccess(t: T?)

    /**
     * 统一处理失败，比如登录失效等
     *
     * @param code
     * @param msg
     */
    open fun onError(code: Int, msg: String?) {

    }

}