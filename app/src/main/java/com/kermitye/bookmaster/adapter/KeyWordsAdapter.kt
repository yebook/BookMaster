package com.kermitye.bookmaster.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.bookmaster.R

/**
 * Created by kermitye on 2018/10/8 14:44
 */
class KeyWordsAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_txt_icon, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.mTvTxt, item)
    }
}