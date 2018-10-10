package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView
import com.kermitye.bookmaster.model.bean.BookDetailBean
import com.kermitye.bookmaster.model.bean.BookReviewBean
import com.kermitye.bookmaster.model.bean.Review
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/10/8 17:37
 */
interface BookDetailContract {
    abstract class BookDetailPresenter: BasePresenter<IBookDetailModel, IBookDetailView>()

    interface IBookDetailModel: IBaseModel {
        fun getBookDetail(bookId: String) : Observable<BookDetailBean>
        fun getBookReview(bookId: String, start: Int = 0, limit: Int = 2) : Observable<BookReviewBean>
    }

    interface IBookDetailView : IBaseView {
        fun updateData(data: BookDetailBean?)
        fun updateBookReview(data: List<Review>)
    }
}