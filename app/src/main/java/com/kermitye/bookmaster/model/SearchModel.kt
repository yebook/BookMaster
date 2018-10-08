package com.kermitye.bookmaster.model

import com.huiming.bestnamerobot.rx.RetrofitUtil
import com.huiming.bestnamerobot.rx.Rx
import com.kermitye.bookmaster.api.ZhuiShuApi
import com.kermitye.bookmaster.contract.BookshelfContract
import com.kermitye.bookmaster.contract.SearchContract
import com.kermitye.bookmaster.model.bean.HotWordBean
import com.kermitye.bookmaster.model.bean.KeyWordsBean
import com.kermitye.bookmaster.model.bean.SearchBooksBean
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/9/28 11:09
 */
class SearchModel : SearchContract.ISearchModel {


    companion object {
        fun newInstance(): SearchModel = SearchModel()
    }

    override fun getHotWord(): Observable<HotWordBean> {
        return Rx.get(createApi().getHotWord())
    }

    override fun getKeyWrods(query: String): Observable<KeyWordsBean> {
        return Rx.get(createApi().getKeyWords(query))
    }

    override fun getSearchBooks(query: String): Observable<SearchBooksBean> {
        return Rx.get(createApi().getSearchBooks(query))
    }


    fun createApi() = RetrofitUtil.createApi(ZhuiShuApi::class.java, ZhuiShuApi.BASE_URL)

}