package com.kermitye.bookmaster.model

import com.huiming.bestnamerobot.rx.RetrofitUtil
import com.huiming.bestnamerobot.rx.Rx
import com.kermitye.bookmaster.api.ZhuiShuApi
import com.kermitye.bookmaster.contract.BookDetailContract
import com.kermitye.bookmaster.model.bean.BookDetailBean
import com.kermitye.bookmaster.model.bean.BookReviewBean
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/10/8 17:56
 */
class BookDetailModel: BookDetailContract.IBookDetailModel {
    companion object {
        fun newInstance(): BookDetailModel = BookDetailModel()
    }

    override fun getBookDetail(bookId: String): Observable<BookDetailBean> {
        return Rx.get(createApi().getBookDetail(bookId))
    }

    override fun getBookReview(bookId: String, start: Int, limit: Int): Observable<BookReviewBean> {
        return Rx.get(createApi().getBookReview(bookId, start, limit))
    }

    fun createApi() = RetrofitUtil.createApi(ZhuiShuApi::class.java, ZhuiShuApi.BASE_URL)

}
