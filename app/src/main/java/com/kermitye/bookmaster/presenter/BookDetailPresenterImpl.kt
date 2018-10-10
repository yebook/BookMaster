package com.kermitye.bookmaster.presenter

import com.kermitye.baselib.ext.excute
import com.kermitye.baselib.net.HttpObserver
import com.kermitye.bookmaster.contract.BookDetailContract
import com.kermitye.bookmaster.model.BookDetailModel
import com.kermitye.bookmaster.model.bean.BookDetailBean
import com.kermitye.bookmaster.model.bean.BookReviewBean

/**
 * Created by kermitye on 2018/10/8 18:14
 */
class BookDetailPresenterImpl : BookDetailContract.BookDetailPresenter() {

    companion object {
        fun newInstance(): BookDetailPresenterImpl = BookDetailPresenterImpl()
    }

    override fun getModel(): BookDetailContract.IBookDetailModel = BookDetailModel.newInstance()

    override fun onStart() {
    }

    fun getBookData(bookId: String) {
        mModel?.getBookDetail(bookId)?.excute(object : HttpObserver<BookDetailBean>() {
            override fun onSuccess(t: BookDetailBean?) {
                mView?.updateData(t)
            }

            override fun onError(code: Int, msg: String?) {
            }
        })
    }

    fun getBookReview(bookId: String) {
        mModel?.getBookReview(bookId)?.excute(object : HttpObserver<BookReviewBean>() {
            override fun onSuccess(t: BookReviewBean?) {
                t?.let {
                    mView?.updateBookReview(t.reviews)
                }
            }
        })
    }

}