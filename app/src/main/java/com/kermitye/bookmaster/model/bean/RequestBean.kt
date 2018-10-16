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

data class BookDetailBean(
    val _id: String?,
    val author: String?,
    val cover: String?,
    val creater: String?,
    val longIntro: String?,
    val title: String?,
    val majorCate: String?,
    val minorCate: String?,
    val majorCateV2: String?,
    val minorCateV2: String?,
    val hiddenPackage: List<Any?>?,
    val apptype: List<Int?>?,
    val rating: Rating?,
    val hasCopyright: Boolean?,
    val buytype: Int?,
    val sizetype: Int?,
    val superscript: String?,
    val currency: Int?,
    val contentType: String?,
    val _le: Boolean?,
    val allowMonthly: Boolean?,
    val allowVoucher: Boolean?,
    val allowBeanVoucher: Boolean?,
    val hasCp: Boolean?,
    val postCount: Int?,
    val latelyFollower: Int?,
    val followerCount: Int?,
    val wordCount: Int,
    val serializeWordCount: Int?,
    val retentionRatio: String?,
    val updated: String?,
    val isSerial: Boolean,
    val chaptersCount: Int?,
    val lastChapter: String?,
    val gender: List<String?>?,
    val tags: List<String?>?,
    val advertRead: Boolean?,
    val cat: String?,
    val donate: Boolean?,
    val copyright: String?,
    val _gg: Boolean?,
    val isForbidForFreeApp: Boolean?,
    val discount: Any?,
    val limit: Boolean?
)

data class Rating(
    val count: Int?,
    val score: Double?,
    val isEffect: Boolean?
)


data class BookReviewBean(
    val total: Int,
    val today: Int,
    val reviews: List<Review>,
    val ok: Boolean
)

data class Review(
    val _id: String,
    val rating: Int,
    val author: Author,
    val helpful: Helpful,
    val likeCount: Int,
    val state: String,
    val updated: String,
    val created: String,
    val commentCount: Int,
    val content: String,
    val title: String
)

data class Author(
    val _id: String,
    val avatar: String,
    val nickname: String,
    val activityAvatar: String,
    val type: String,
    val lv: Int,
    val gender: String
)

data class Helpful(
    val total: Int,
    val yes: Int,
    val no: Int
)


data class RankingBean(
    val male: List<Male>,
    val picture: List<Picture>,
    val epub: List<Epub>,
    val female: List<Female>,
    val ok: Boolean
)

data class Female(
    val _id: String,
    val title: String,
    val cover: String,
    val collapse: Boolean,
    val monthRank: String,
    val totalRank: String,
    val shortTitle: String
)

data class Epub(
    val _id: String,
    val title: String,
    val cover: String,
    val collapse: Boolean,
    val shortTitle: String
)

data class Male(
    val _id: String,
    val title: String,
    val cover: String,
    val collapse: Boolean,
    val monthRank: String,
    val totalRank: String,
    val shortTitle: String
)

data class Picture(
    val _id: String,
    val title: String,
    val cover: String,
    val collapse: Boolean,
    val shortTitle: String
)


data class RankingBookBean(
    val ranking: Ranking,
    val ok: Boolean
)

data class Ranking(
    val _id: String,
    val updated: String,
    val title: String,
    val tag: String,
    val cover: String,
    val icon: String,
    val __v: Int,
    val monthRank: String,
    val totalRank: String,
    val shortTitle: String,
    val created: String,
    val biTag: String,
    val isSub: Boolean,
    val collapse: Boolean,
    val new: Boolean,
    val gender: String,
    val priority: Int,
    val books: List<SearchBook>,
    val id: String,
    val total: Int
)
