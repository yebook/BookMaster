package com.kermitye.bookmaster.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kermitye.bookmaster.MyApp
import com.kermitye.bookmaster.R
import kotlinx.android.synthetic.main.item_bookshelf.view.*

/**
 * Created by kermitye on 2018/9/27 14:26
 */
class BookshelfAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_bookshelf, data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.let {
            it.setText(R.id.mTvBookName, item)
            //设置图片圆角角度
            val roundedCorners = RoundedCorners(20)
            //通过RequestOptions扩展功能
            val options = RequestOptions.bitmapTransform(roundedCorners)//.override(300, 300)
            Glide.with(MyApp.instance).load(R.mipmap.head).apply(options).into(helper.itemView.mIvCover)
        }
    }

}