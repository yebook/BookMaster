package com.kermitye.bookmaster.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.api.ZhuiShuApi
import com.kermitye.bookmaster.manager.ImageManager
import com.kermitye.bookmaster.model.bean.Review

/**
 * Created by kermitye on 2018/10/10 14:54
 */
class BookReviewAdapter(data: List<Review>) : BaseQuickAdapter<Review, BaseViewHolder>(R.layout.item_review, data) {

    override fun convert(helper: BaseViewHolder?, item: Review?) {
        if (helper != null && item != null) {
            ImageManager.displayImageToCir(ZhuiShuApi.IMG_URL+item.author.avatar, helper.getView(R.id.mIvUserHead))
            helper.setText(R.id.mTvNickName, item.author.nickname)
            helper.setText(R.id.mTvTitle, item.title)
            helper.setText(R.id.mTvContent, item.content)
        }
    }
}