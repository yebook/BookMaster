package com.kermitye.bookmaster.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.baselib.ext.setVisible
import com.kermitye.bookmaster.R
import kotlinx.android.synthetic.main.item_dialog_list.view.*

/**
 * Created by kermitye on 2018/10/22 18:12
 */
class ListDialogAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_dialog_list, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.mTvText, item)
    }
}