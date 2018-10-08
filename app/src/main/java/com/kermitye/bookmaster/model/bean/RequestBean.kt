package com.kermitye.bookmaster.model.bean

/**
 * Created by kermitye on 2018/10/8 10:49
 */
data class HotWordBean(
    val hotWords: List<String>,
    val newHotWords: List<NewHotWord>,
    val ok: Boolean
)

data class NewHotWord(
    val word: String,
    val book: String
)


data class KeyWordsBean(
    val keywords: List<String>,
    val ok: Boolean
)


data class SearchBooksBean(
    val books: List<SearchBook>?,
    val total: Int?,
    val ok: Boolean?
)

data class SearchBook(
    val _id: String?,
    val hasCp: Boolean?,
    val title: String?,
    val aliases: String?,
    val cat: String?,
    val author: String?,
    val site: String?,
    val cover: String?,
    val shortIntro: String?,
    val lastChapter: String?,
    val retentionRatio: Double?,
    val banned: Int?,
    val allowMonthly: Boolean?,
    val latelyFollower: Int?,
    val wordCount: Int?,
    val contentType: String?,
    val superscript: String?,
    val sizetype: Int?,
    val highlight: Highlight?
)

data class Highlight(
    val title: List<String>?
)