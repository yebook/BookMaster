package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView
import com.kermitye.bookmaster.model.bean.HotWordBean
import com.kermitye.bookmaster.model.bean.KeyWordsBean
import com.kermitye.bookmaster.model.bean.SearchBook
import com.kermitye.bookmaster.model.bean.SearchBooksBean
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/9/28 16:12
 */
interface SearchContract {
    abstract class SearchPresenter : BasePresenter<ISearchModel, ISearchView>()

    interface ISearchModel : IBaseModel {
        fun getHotWord() : Observable<HotWordBean>
        fun getKeyWrods(query: String) : Observable<KeyWordsBean>
        fun getSearchBooks(query: String): Observable<SearchBooksBean>
    }

    interface ISearchView: IBaseView {
        fun updateHotWord(data: List<String>)
        fun updateKeyWords(data: List<String>)
        fun updateSearchBooks(data: List<SearchBook>)
    }

}