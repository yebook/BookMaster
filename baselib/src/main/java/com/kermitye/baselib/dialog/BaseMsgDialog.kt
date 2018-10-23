package com.kermitye.baselib.dialog

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kermitye.baselib.R
import com.trello.rxlifecycle2.components.support.RxDialogFragment
import android.view.WindowManager
import android.view.Gravity
import com.kermitye.baselib.util.BaseUtils
import me.yokeyword.fragmentation.SupportFragment
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by kermitye on 2018/10/18 17:41
 */
abstract class BaseMsgDialog : SupportDialogFragment() {

    companion object {
        private val MARGIN = "margin"
        private val WIDTH = "width"
        private val HEIGHT = "height"
        private val DIM = "dim_amount"
        private val BOTTOM = "show_bottom"
        private val CANCEL = "out_cancel"
        private val ANIM = "anim_style"
        private val LAYOUT = "layout_id"
    }

    lateinit var mActivity: AppCompatActivity
    private var margin: Int = 0//左右边距
    private var width: Int = 0//宽度
    private var height: Int = 0//高度
    private var dimAmount = 0.5f//灰度深浅
    private var showBottom = false//是否底部显示
    private var outCancel = true//是否点击外部取消
    val dismissed = AtomicBoolean(false)
    @StyleRes
    private var animStyle: Int = 0
    @LayoutRes
    protected var layoutId: Int = 0

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let { mActivity = context as AppCompatActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MsgDialog)
        layoutId = intLayoutId()

        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN)
            width = savedInstanceState.getInt(WIDTH)
            height = savedInstanceState.getInt(HEIGHT)
            dimAmount = savedInstanceState.getFloat(DIM)
            showBottom = savedInstanceState.getBoolean(BOTTOM)
            outCancel = savedInstanceState.getBoolean(CANCEL)
            animStyle = savedInstanceState.getInt(ANIM)
            layoutId = savedInstanceState.getInt(LAYOUT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        convertView(DialogViewHolder.create(view), this)
        return view
    }

    abstract fun intLayoutId(): Int

    abstract fun convertView(holderDialog: DialogViewHolder, dialog: BaseMsgDialog)

    override fun onStart() {
        super.onStart()
        initParams()
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MARGIN, margin)
        outState.putInt(WIDTH, width)
        outState.putInt(HEIGHT, height)
        outState.putFloat(DIM, dimAmount)
        outState.putBoolean(BOTTOM, showBottom)
        outState.putBoolean(CANCEL, outCancel)
        outState.putInt(ANIM, animStyle)
        outState.putInt(LAYOUT, layoutId)
    }

    private fun initParams() {
        val window = dialog.window
        if (window != null) {
            val lp = window.attributes
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount
            //是否在底部显示
            if (showBottom) {
                lp.gravity = Gravity.BOTTOM;
                if (animStyle == 0) {
                    animStyle = R.style.DialogAnimation
                }
            }

            //设置dialog宽度
            if (width === 0) {
                lp.width = BaseUtils.getScreenWidth(context) - 2 * BaseUtils.dp2px(context, margin.toFloat())
            } else if (width === -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.width = BaseUtils.dp2px(context, width.toFloat())
            }

            //设置dialog高度
            if (height === 0) {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            } else {
                lp.height = BaseUtils.dp2px(context, height.toFloat())
            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle)
            window.attributes = lp
        }
        isCancelable = outCancel
    }

    fun setMargin(margin: Int) = apply { this.margin = margin }

    fun setWidth(width: Int): BaseMsgDialog {
        this.width = width
        return this
    }

    fun setHeight(height: Int): BaseMsgDialog {
        this.height = height
        return this
    }

    fun setDimAmount(dimAmount: Float): BaseMsgDialog {
        this.dimAmount = dimAmount
        return this
    }

    fun setShowBottom(showBottom: Boolean): BaseMsgDialog {
        this.showBottom = showBottom
        return this
    }

    fun setOutCancel(outCancel: Boolean): BaseMsgDialog {
        this.outCancel = outCancel
        return this
    }

    fun setAnimStyle(@StyleRes animStyle: Int): BaseMsgDialog {
        this.animStyle = animStyle
        return this
    }

    fun show(manager: FragmentManager): BaseMsgDialog {
        val ft = manager.beginTransaction()
        if (this.isAdded) {
            ft.remove(this).commit()
        }
        ft.add(this, System.currentTimeMillis().toString())
        ft.commitAllowingStateLoss()
        return this
    }

}