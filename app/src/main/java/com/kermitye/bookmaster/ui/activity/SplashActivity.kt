package com.kermitye.bookmaster.ui.activity

import android.os.Bundle
import com.gyf.barlibrary.BarHide
import com.kermitye.baselib.ui.BaseActivity
import com.kermitye.bookmaster.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.startActivity
import java.util.concurrent.TimeUnit

/**
 * Created by kermitye
 * Date: 2018/9/21 14:15
 * Desc:
 */
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_splash)
        mImmersionBar?.let { it.hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init() }  //隐藏导航栏
        var disposable = Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            startActivity<MainActivity>()
            finish()
        }

        /*mTvJump.setOnClickListener {
            if (!disposable.isDisposed)
                disposable.dispose()
            startActivity<MainActivity>()
            finish()
        }*/
    }

    /*fun checkPermission() = RxPermissions(this).request( )
            .subscribe({
                if (!it) {
                    toast("请获取权限后使用")
                }
            })*/
}