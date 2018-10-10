package com.kermitye.bookmaster.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.adapter.BookReviewAdapter
import com.kermitye.bookmaster.contract.BookDetailContract
import com.kermitye.bookmaster.ext.getStatusHeight
import com.kermitye.bookmaster.manager.ImageManager
import com.kermitye.bookmaster.model.bean.BookDetailBean
import com.kermitye.bookmaster.model.bean.Review
import com.kermitye.bookmaster.presenter.BookDetailPresenterImpl
import com.kermitye.bookmaster.util.ToolUtils
import kotlinx.android.synthetic.main.activity_book_detail.*

/**
 * Created by kermitye on 2018/10/8 18:53
 */
class BookDetailActivity : MvpActivity<BookDetailPresenterImpl>(), BookDetailContract.IBookDetailView {

    var mReviews = arrayListOf<Review>()
    val mReviewAdapter by lazy { BookReviewAdapter(mReviews) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_book_detail)
        initView()
        initListener()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.let { it.statusBarView(mTopView).init() }
    }

    override fun initPresenter(): BookDetailPresenterImpl = BookDetailPresenterImpl.newInstance()

    override fun attachMV() {
        mPresenter.attachMV(this)
    }

    fun initView() {
        var bookId = intent.getStringExtra("id")
        if (bookId.isNullOrEmpty()) {
            toast("获取书籍错误")
            return
        }
        mPresenter.getBookData(bookId)
        mPresenter.getBookReview(bookId)

        mRvReview.layoutManager = LinearLayoutManager(this)
        mRvReview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRvReview.adapter = mReviewAdapter
    }

    fun initListener() {
        mIvBack.setOnClickListener { onBackPressedSupport() }
        //状态栏+标题栏的高度
        var topHeight = mRlBar.layoutParams.height + getStatusHeight()
        //监听滑动状态设置透明度
        mScrollView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            var headHight = mIvBookHeadBg.layoutParams.height
            var flagHight = headHight - topHeight - 50
            var alpha = 0f

            if (flagHight > 0) {
                if (scrollY >= (headHight - topHeight)) {
                    alpha = 1f
                } else if (scrollY >= flagHight) {
//                    alpha = scrolledY.toFloat() / (flagHight + 50)
                    alpha = (scrollY - flagHight).toFloat() / 50
                } else {
                    alpha = 0f
                }
            }
            LogUtil.e("====scrool: $scrollY / $flagHight / $headHight / $topHeight / $alpha")
            mTvTitle.alpha = alpha
            var alphaValue = (alpha * 100).toInt()
            var colorValue = "#${if (alphaValue < 10) "0" + alphaValue else alphaValue}00b0ff"
            if (alphaValue >= 99)
                colorValue = "#00b0ff"
            mLlHead.setBackgroundColor(if (alpha == 0f) Color.TRANSPARENT else Color.parseColor(colorValue))
        }

        mLlCatalog.setOnClickListener { toast("进入目录") }
        mTvAllReview.setOnClickListener { toast("进入评论") }
    }

    override fun updateData(data: BookDetailBean?) {
        data?.let {
            mTvTitle.text = data.title
            mTvBookName.text = data.title
            mTvAuthor.text = data.author
            mEtv.setContent(data.longIntro)
            var count = if (data.wordCount > 10000) "${data.wordCount / 10000}万字" else "${data.wordCount}字"
            mTvInfo.text = "${data.cat} | $count | ${if (data.isSerial) "连载" else "完结"}"
            if (data.cover != null && data.cover.contains("/agent/")) {
                var url = data.cover.substring(7, data.cover.length)
                url = url.replace("%2F", "/").replace("%3A", ":")
                ImageManager.displayImage(url, mIvBookHead)
                ImageManager.displayImageToBlur(url, mIvBookHeadBg)
            }
            mTvUpdated.text = ToolUtils.getShortTime(data.updated?.replace(Regex("T|Z"), " ") ?: "")
            mTvFollower.text = "追书人数\n${data.latelyFollower}"
            mTvRetentionRatio.text = "读者留存率\n${data.retentionRatio}"
            mTvScore.text = "评分\n${data.rating?.score}"
            mTvLastChapter.text = data.lastChapter
        }
    }

    override fun updateBookReview(data: List<Review>) {
        mReviews.clear()
        mReviews.addAll(data)
        mReviewAdapter.notifyDataSetChanged()
    }
}