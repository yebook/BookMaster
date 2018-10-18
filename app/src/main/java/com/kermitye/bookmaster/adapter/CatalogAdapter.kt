package com.kermitye.bookmaster.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.manager.ImageManager
import com.kermitye.bookmaster.model.bean.Chapter
import com.kermitye.bookmaster.model.bean.SearchBook
import com.kermitye.bookmaster.model.bean.SearchBooksBean

/**
 * Created by kermitye on 2018/10/8 15:58
 */
class CatalogAdapter(data: List<Chapter>) : BaseQuickAdapter<Chapter, BaseViewHolder>(R.layout.item_catalog, data) {

    override fun convert(helper: BaseViewHolder?, item: Chapter?) {
        helper?.setText(R.id.mTvChapter, item?.title)
        helper?.setVisible(R.id.mIvVip, item?.isVip ?: false)
    }
}