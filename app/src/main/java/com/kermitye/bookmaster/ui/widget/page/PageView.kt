package com.kermitye.bookmaster.ui.widget.page

import android.content.Context
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Created by kermitye on 2018/10/25 15:55
 */
class PageView @kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {
    private val TAG = "BookPageWidget"

    private var mViewWidth = 0 // 当前View的宽
    private var mViewHeight = 0 // 当前View的高

    private var mStartX = 0
    private var mStartY = 0
    private var isMove = false
    // 初始化参数
    private var mBgColor = -0x313d64
    private var mPageMode = PageMode.SIMULATION
    // 是否允许点击
    private var mCanTouch = true
    // 唤醒菜单的区域
    private var mCenterRect: RectF? = null
    private var isPrepare: Boolean = false
    /**
     * 点击监听
     */
    private lateinit var mTouchListener: TouchListener
    //内容加载器
    private lateinit var mPageLoader: PageLoader
    // 动画类
    private var mPageAnim: PageAnimation? = null
    //动画类监听
    private var mPageAnimListener = object : PageAnimation.OnPageChangeListener {
        override fun pageCancel() = this@PageView.pageCancel()

        override fun hasNext(): Boolean = hasNextPage()

        override fun hasPrev(): Boolean = hasPrevPage()
    }



    private fun hasPrevPage() : Boolean {
        //TODO 实现
        return false
    }

    private fun hasNextPage() : Boolean {
        //TODO 实现
        return false
    }

    private fun pageCancel() {
        //TODO 实现
    }




    interface TouchListener {
        fun onTouch(): Boolean
        fun center()
        fun prePage()
        fun nextPage()
        fun cancel()

    }
}