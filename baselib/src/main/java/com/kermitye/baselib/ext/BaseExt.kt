package com.kermitye.baselib.ext

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kermitye.baselib.net.BaseConvert
import com.kermitye.baselib.net.BaseResp
import com.kermitye.baselib.net.HttpObserver
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KProperty

/**
 * Created by kermitye
 * Date: 2018/9/20 10:17
 * Desc:
 */

object BaseExt {
    fun <T> preference(context: Context, name: String, default: T) = Preference(context, name, default)
}

//---------------------------RX EXT-------------------------------------------

fun <T> Observable<T>.excute(subscriber: HttpObserver<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

/*
    扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseConvert())
}

//---------------------------VIEW EXT-------------------------------------------

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction { add(frameId, fragment) }

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) = supportFragmentManager.inTransaction { replace(frameId, fragment) }

/**
 * 扩展视图可见性
 */
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}



//---------------------------SP EXT-------------------------------------------

class Preference<T>(private val context: Context, val name: String, val default: T) {
    val prefs: SharedPreferences by lazy { context.getSharedPreferences("bestNameRobot", Context.MODE_PRIVATE) }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getSharedPreferences(name, default)

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = putSharedPreferences(name, value)

    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when(value) {
            is Int -> putInt(name, value)
            is Float -> putFloat(name, value)
            is Long -> putLong(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> throw IllegalArgumentException("SharedPreference can't be save this type")
        }.apply()
    }

    private fun getSharedPreferences(name:String, default: T): T = with(prefs) {
        val res: Any = when(default) {
            is Int -> getInt(name, default)
            is Float -> getFloat(name, default)
            is Long -> getLong(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> throw IllegalArgumentException("SharedPreference can't be get this type")
        }
        return res as T
    }
}
