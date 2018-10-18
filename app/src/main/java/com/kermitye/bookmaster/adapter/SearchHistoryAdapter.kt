package com.kermitye.bookmaster.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.baselib.ext.setVisible
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.util.ToolUtils
import kotlinx.android.synthetic.main.item_txt_icon.view.*

/**
 * Created by kermitye on 2018/10/8 14:44
 */
class SearchHistoryAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_txt_icon, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.itemView?.let {
            it.mTvTxt.textSize = 12f
            it.mIvIcon.setVisible(true)
            it.mIvIcon.setImageResource(R.drawable.ic_search_black)
        }
        helper?.setText(R.id.mTvTxt, item)
    }
}