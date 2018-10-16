package com.kermitye.bookmaster.api

import com.kermitye.bookmaster.model.bean.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by kermitye on 2018/10/8 10:41
 */
interface ZhuiShuApi {
    companion object {
        const val BASE_URL = "http://api.zhuishushenqi.com"
        const val IMG_URL = "http://statics.zhuishushenqi.com"
    }

    @GET("/book/hot-word")
    fun getHotWord(): Observable<HotWordBean>

    @GET("/book/auto-complete")
    fun getKeyWords(@Query("query") query: String): Observable<KeyWordsBean>

    @GET("/book/fuzzy-search")
    fun getSearchBooks(@Query("query") query: String): Observable<SearchBooksBean>

    @GET("/book/{bookid}")
    fun getBookDetail(@Path("bookid") bookid: String): Observable<BookDetailBean>

    @GET("/post/review/by-book?sort=updated")
    fun getBookReview(@Query("book") bookid: String, @Query("start") start: Int = 0, @Query("limit") limit: Int = 2): Observable<BookReviewBean>

    @GET("/ranking/gender")
    fun getRanking() : Observable<RankingBean>

    @GET("/ranking/{rankingid}")
    fun getRankingBooks(@Path("rankingid") rankingId: String): Observable<RankingBookBean>
}