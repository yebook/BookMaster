package com.kermitye.baselib.dialog

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import com.kermitye.baselib.R
import com.trello.rxlifecycle2.components.support.RxDialogFragment
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragmentDelegate
import me.yokeyword.fragmentation.anim.FragmentAnimator
import java.util.concurrent.atomic.AtomicBoolean



/**
 * Created by kermitye on 2018/10/18 17:41
 */
abstract class BaseMsgDialog : RxDialogFragment() {

    companion object {
        private val MARGIN = "margin"
        private val WIDTH = "width"
        private val HEIGHT = "height"
        private val DIM = "dim_amount"
        private val POSITION = "position"
        private val CANCEL = "out_cancel"
        private val ANIM = "anim_style"
        private val LAYOUT = "layout_id"
    }

    lateinit var mActivity: AppCompatActivity
    private val margin: Int = 0//左右边距
    private val width: Int = 0//宽度
    private val height: Int = 0//高度
    private val dimAmount = 0.5f//灰度深浅
    private val position: Int = 0//显示的位置
    private val outCancel = true//是否点击外部取消
    @StyleRes
    private val animStyle: Int = 0
    @LayoutRes
    protected var layoutId: Int = 0

    val dismissed = AtomicBoolean(false)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let { mActivity = context as AppCompatActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.NiceDialog)
        layoutId = intLayoutId()

    }

    abstract fun intLayoutId(): Int

    abstract fun convertView(holder: ViewHolder, dialog: BaseMsgDialog)


}