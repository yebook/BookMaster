package com.kermitye.bookmaster.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.manager.ImageManager
import com.kermitye.bookmaster.model.bean.SearchBook
import com.kermitye.bookmaster.model.bean.SearchBooksBean

/**
 * Created by kermitye on 2018/10/8 15:58
 */
class SearchBooksAdapter(data: List<SearchBook>) : BaseQuickAdapter<SearchBook, BaseViewHolder>(R.layout.item_search_books, data) {

    override fun convert(helper: BaseViewHolder?, item: SearchBook?) {
        helper?.let {
            if (item != null) {
                if (item.cover != null && item.cover.contains("/agent/")) {
                    var url = item.cover.substring(7, item.cover.length)
                    url = url.replace("%2F", "/").replace("%3A", ":")
                    ImageManager.displayImage(url, it.getView(R.id.mIvCover))
                } else {
                    ImageManager.displayImage(item.cover ?: "", it.getView(R.id.mIvCover))
                }
                it.setText(R.id.mTvBookName, item.title)
                it.setText(R.id.mTvAuthor, item.author)
                it.setText(R.id.mTvShortInfo, "简介：${item.shortIntro}")
            }
        }
    }
}