package com.kermitye.bookmaster.presenter

import com.kermitye.bookmaster.contract.BookDetailContract
import com.kermitye.bookmaster.model.BookDetailModel

/**
 * Created by kermitye on 2018/10/8 18:14
 */
class BookDetailPresenterImpl: BookDetailContract.BookDetailPresenter() {

    companion object {
        fun newInstance(): BookDetailPresenterImpl = BookDetailPresenterImpl()
    }

    override fun getModel(): BookDetailContract.IBookDetailModel = BookDetailModel.newInstance()

    override fun onStart() {

    }



}