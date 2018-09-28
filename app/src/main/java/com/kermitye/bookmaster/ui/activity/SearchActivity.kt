package com.kermitye.bookmaster.ui.activity

import android.os.Bundle
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.baselib.mvp.MvpFragment
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.contract.SearchContract
import com.kermitye.bookmaster.presenter.SearchPresenterImpl
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by kermitye on 2018/9/28 16:11
 */
class SearchActivity : MvpActivity<SearchPresenterImpl>(), SearchContract.ISearchView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_search)
        initView()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.let { it.statusBarView(mTopView).init() }
    }

    override fun initPresenter(): SearchPresenterImpl = SearchPresenterImpl.newInstance()

    override fun attachMV() {
        mPresenter.attachMV(this)
    }


    fun initView() {

    }
}