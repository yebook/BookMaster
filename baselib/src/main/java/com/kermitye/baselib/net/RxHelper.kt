package com.huiming.bestnamerobot.rx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException

/**
 * Created by kermitye
 * Date: 2018/7/18 13:43
 * Desc:
 */

object Rx {

    fun <T> get(observable: Observable<T>, lifecycleOwner: LifecycleOwner? = null): Observable<T> {
        if (lifecycleOwner == null) {
            return observable
        } else {
            return observable.bindUntilEvent(lifecycleOwner, Lifecycle.Event.ON_DESTROY)
        }
    }

    fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .onErrorResumeNext { t: Throwable ->
                        if (t is SocketTimeoutException)
                            Observable.error(Throwable("请求超时"))
                        else
                            Observable.error(t)
                    }
        }
    }
}