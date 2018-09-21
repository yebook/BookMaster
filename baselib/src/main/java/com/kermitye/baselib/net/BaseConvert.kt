package com.kermitye.baselib.net

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 * Created by kermitye
 * Date: 2018/9/4 17:43
 * Desc:
 */
class BaseConvert<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (!t.success) {
            return Observable.error(BaseException(t.code, t.msg))
        }
        return Observable.just(t.data)
    }
}