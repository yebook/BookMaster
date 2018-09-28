package com.kermitye.bookmaster.presenter

import com.kermitye.bookmaster.contract.BookshelfContract
import com.kermitye.bookmaster.model.BookshelfModel

/**
 * Created by kermitye on 2018/9/28 11:06
 */
class BookshelfPresenterImpl: BookshelfContract.BookshelfPresenter() {

    companion object {
        fun newInstance(): BookshelfPresenterImpl = BookshelfPresenterImpl()
    }

    override fun getModel(): BookshelfContract.IBookshelfModel = BookshelfModel.newInstance()


    override fun onStart() {
    }



}