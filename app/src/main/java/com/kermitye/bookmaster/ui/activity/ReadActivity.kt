package com.kermitye.bookmaster.ui.activity

import android.os.Bundle
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.contract.ReadContract
import com.kermitye.bookmaster.presenter.ReadPresenterImpl

/**
 * Created by kermitye on 2018/10/24 15:42
 */
class ReadActivity : MvpActivity<ReadPresenterImpl>(), ReadContract.IReadView {
    override fun initPresenter(): ReadPresenterImpl = ReadPresenterImpl.newInstance()

    override fun attachMV() = mPresenter.attachMV(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
    }
}