package com.kermitye.bookmaster.ui.activity

import android.os.Bundle
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.contract.BookDetailContract
import com.kermitye.bookmaster.presenter.BookDetailPresenterImpl

/**
 * Created by kermitye on 2018/10/8 18:53
 */
class BookDetailActivity: MvpActivity<BookDetailPresenterImpl>(), BookDetailContract.IBookDetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_book_detail)
    }

    override fun initPresenter(): BookDetailPresenterImpl = BookDetailPresenterImpl.newInstance()

    override fun attachMV() {
        mPresenter.attachMV(this)
    }


}