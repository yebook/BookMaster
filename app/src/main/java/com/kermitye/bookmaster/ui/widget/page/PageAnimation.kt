package com.kermitye.bookmaster.ui.widget.page

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Scroller

/**
 * Created by kermitye on 2018/10/25 17:57
 */
abstract class PageAnimation(mScreenWidth: Int, mScreenHeight: Int, mMarginWidth: Int = 0, mMarginHeight: Int = 0, var mView: View?, mListener: OnPageChangeListener) {
    //滑动装置
    protected lateinit var mScroller: Scroller
    //移动方向
    var mDirection = Direction.NONE

    var isRunning = false
    //视图的尺寸
    protected var mViewWidth: Int = 0
    protected var mViewHeight: Int = 0
    //起始点
    protected var mStartX: Float = 0.toFloat()
    protected var mStartY: Float = 0.toFloat()
    //触碰点
    protected var mTouchX: Float = 0.toFloat()
    protected var mTouchY: Float = 0.toFloat()
    //上一个触碰点
    protected var mLastX: Float = 0.toFloat()
    protected var mLastY: Float = 0.toFloat()

    init {
        mViewWidth = mScreenWidth - mMarginWidth * 2
        mViewHeight = mScreenHeight - mMarginHeight * 2
        mScroller = Scroller(mView?.context, LinearInterpolator())
    }

    fun setStartPoint(x: Float, y: Float) {
        mStartX = x
        mStartY = y

        mLastX = mStartX
        mLastY = mStartY
    }

    fun setTouchPoint(x: Float, y: Float) {
        mLastX = mTouchX
        mLastY = mTouchY

        mTouchX = x
        mTouchY = y
    }

    /**
     * 开启动画
     */
    fun startAnim() {
        if (isRunning) {
            return
        }
        isRunning = true
    }

    fun clear() = { mView = null }

    /**
     * 点击事件处理
     */
    abstract fun onTouchEvent(event: MotionEvent)

    /**
     * 绘制图形
     */
    abstract fun draw(canvas: Canvas)

    /**
     * 滚动动画
     * 必须放在computeScroll()方法中执行
     */
    abstract fun scrollAnim()

    /**
     * 取消动画
     */
    abstract fun cancleAnim()

    /**
     * 获取背景板
     */
    abstract fun getBgBitmap() : Bitmap

    /**
     * 获取内容显示版本
     */
    abstract fun getNextBitmap() : Bitmap



    enum class Direction(val isHorizontal: Boolean) {
        NONE(true), NEXT(true), PRE(true), UP(false), DOWN(false)
    }

    interface OnPageChangeListener {
        fun hasPrev(): Boolean
        operator fun hasNext(): Boolean
        fun pageCancel()
    }

}