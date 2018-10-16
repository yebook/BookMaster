package com.kermitye.bookmaster.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.kermitye.baselib.ext.setVisible

/**
 * Created by kermitye on 2018/10/15 18:09
 */
class StateLayout(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    companion object {
        const val TYPE_CONTENT = 0
        const val TYPE_LOADING = 1
        const val TYPE_ERROR = 2
        const val TYPE_EMPTY = 3
    }

    private var mConfig = StateLayoutConfig.newInstance()
    private var mLoadingView: View? = null
    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mOtherResIds = arrayListOf<Int>()

    fun setConfig(config: StateLayoutConfig) {
        this.mConfig = config
    }

    fun getConfig() = mConfig

    fun showView(type: Int) {
        when (type) {
            TYPE_CONTENT -> showContent()
            TYPE_LOADING -> showLoading()
            TYPE_EMPTY -> showEmpty()
            TYPE_ERROR -> showError()
        }
    }

    private fun showLoading() {
        if (mLoadingView == null) {
            mLoadingView = inflateView(mConfig.mLoadingLayout)
            addView(mLoadingView, 0)
            mLoadingView?.let { mOtherResIds.add(it.id) }
        }
        showViewById(mLoadingView?.id ?: -1)
    }

    private fun showContent() {
        var otherIds = arrayListOf(mConfig.mEmptyLayout, mConfig.mErrorLayout, mConfig.mLoadingLayout)
        for (i in 0 until childCount) {
            var view = getChildAt(i)
            view.setVisible(view.id !in mOtherResIds)
        }
    }

    private fun showError() {
        if (mErrorView == null) {
            mErrorView = inflateView(mConfig.mErrorLayout)
            addView(mErrorView, 0)
            mErrorView?.let { mOtherResIds.add(it.id) }
        }
        showViewById(mErrorView?.id ?: -1)
    }

    private fun showEmpty() {
        if (mEmptyView == null) {
            mEmptyView = inflateView(mConfig.mEmptyLayout)
            addView(mEmptyView, 0)
            mEmptyView?.let { mOtherResIds.add(it.id) }
        }
        showViewById(mEmptyView?.id ?: -1)
    }


    private fun showViewById(viewId: Int) {
        for (i in 0 until childCount) {
            var view = getChildAt(i)
            view.setVisible(view.id == viewId)
        }
    }


    private fun inflateView(resId: Int) = LayoutInflater.from(context).inflate(resId, null)


    class StateLayoutConfig private constructor() {
        var mErrorLayout = -1
        var mEmptyLayout = -1
        var mLoadingLayout = -1

        companion object {
            fun newInstance(): StateLayoutConfig = StateLayoutConfig()
        }

        fun setErrorLayout(resId: Int) = apply { this.mErrorLayout = resId }
        fun setEmptyLayout(resId: Int) = apply { this.mEmptyLayout = resId }
        fun setLoadingLayout(resId: Int) = apply { this.mLoadingLayout = resId }

    }
}