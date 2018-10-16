package com.kermitye.bookmaster.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.api.ZhuiShuApi
import com.kermitye.bookmaster.manager.ImageManager
import com.kermitye.bookmaster.model.bean.Male

/**
 * Created by kermitye on 2018/10/10 16:38
 */
class RankingAdapter(data: List<Male>) : BaseQuickAdapter<Male, BaseViewHolder>(R.layout.item_ranking, data) {
    override fun convert(helper: BaseViewHolder?, item: Male?) {
        if (helper != null && item != null) {
            ImageManager.displayImage(ZhuiShuApi.IMG_URL + item.cover, helper.getView(R.id.mIvRankingIcon))
            helper.setText(R.id.mTvRankingName, item.title)
        }
    }
}