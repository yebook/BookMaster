package com.kermitye.bookmaster.model

import com.huiming.bestnamerobot.rx.RetrofitUtil
import com.huiming.bestnamerobot.rx.Rx
import com.kermitye.bookmaster.api.ZhuiShuApi
import com.kermitye.bookmaster.contract.CatalogContract
import com.kermitye.bookmaster.model.bean.AtocBean
import com.kermitye.bookmaster.model.bean.AtocSourceBean
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * Created by kermitye on 2018/10/17 15:51
 */
class CatalogModel : CatalogContract.ICatalogModel {

    var mSource: ArrayList<AtocSourceBean> = arrayListOf()

    companion object {
        fun newInstance(): CatalogModel = CatalogModel()
    }

    override fun getCatalog(sourceId: String): Observable<AtocBean> {
        return Rx.get(RetrofitUtil.createApi(ZhuiShuApi::class.java, ZhuiShuApi.BASE_URL).getCatalog(sourceId))
    }

    override fun getBookSource(bookId: String): Observable<List<AtocSourceBean>> {
        return Rx.get(RetrofitUtil.createApi(ZhuiShuApi::class.java, ZhuiShuApi.BASE_URL).getBookSource(bookId))
    }

    override fun getData(bookId: String): Observable<AtocBean> {
        return getBookSource(bookId).flatMap { t ->
            if (t.size == 0) {
                Observable.empty<AtocBean>()
            } else {
                mSource.clear()
                mSource.addAll(t)
                getCatalog(t.get(0)._id)
            }
        }
    }
}