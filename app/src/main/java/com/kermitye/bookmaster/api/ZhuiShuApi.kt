package com.kermitye.bookmaster.api

import com.kermitye.bookmaster.model.bean.HotWordBean
import com.kermitye.bookmaster.model.bean.KeyWordsBean
import com.kermitye.bookmaster.model.bean.SearchBooksBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kermitye on 2018/10/8 10:41
 */
interface ZhuiShuApi {
    companion object {
        const val BASE_URL = "http://api.zhuishushenqi.com"
    }


    @GET("/book/hot-word")
    fun getHotWord() : Observable<HotWordBean>

    @GET("/book/auto-complete")
    fun getKeyWords(@Query("query") query: String) : Observable<KeyWordsBean>


    @GET("/book/fuzzy-search")
    fun getSearchBooks(@Query("query") query: String) : Observable<SearchBooksBean>

}