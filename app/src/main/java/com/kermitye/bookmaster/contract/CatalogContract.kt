package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView
import com.kermitye.bookmaster.model.CatalogModel
import com.kermitye.bookmaster.model.bean.AtocBean
import com.kermitye.bookmaster.model.bean.AtocSourceBean
import com.kermitye.bookmaster.model.bean.Chapter
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by kermitye on 2018/10/17 15:43
 */
interface CatalogContract {

    abstract class CatalogPresenter : BasePresenter<CatalogModel, ICatalogView>()

    interface ICatalogModel : IBaseModel {
        fun getCatalog(sourceId: String): Observable<AtocBean>
        fun getBookSource(bookId: String): Observable<List<AtocSourceBean>>
        fun getData(bookId: String): Observable<AtocBean>
    }

    interface ICatalogView : IBaseView {
        fun updateData(data: List<Chapter>)
        fun updateBookSource(data: List<AtocSourceBean>)
    }

}