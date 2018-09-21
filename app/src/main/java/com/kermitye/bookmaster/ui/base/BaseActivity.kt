package com.kermitye.bookmaster.ui.base
import android.os.Bundle
import android.view.MotionEvent
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportActivity
import me.yokeyword.fragmentation.SupportActivityDelegate
import me.yokeyword.fragmentation.anim.FragmentAnimator
import com.kermitye.baselib.ui.BaseActivity as LibBaseActivity
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.ISupportFragment


/**
 * Created by kermitye
 * Date: 2018/9/21 16:09
 * Desc:
 */
open class BaseActivity : LibBaseActivity(), ISupportActivity {
    val mDelegate by lazy { SupportActivityDelegate(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDelegate.onCreate(savedInstanceState);
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDelegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mDelegate.onDestroy()
        super.onDestroy()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
    override fun onBackPressed() {
        super.onBackPressed()
        mDelegate.onBackPressed()
    }

    override fun onBackPressedSupport() {
        mDelegate.onBackPressedSupport()
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator?) {
        mDelegate.setFragmentAnimator(fragmentAnimator)
    }

    override fun getFragmentAnimator(): FragmentAnimator {
        return mDelegate.getFragmentAnimator()
    }



    override fun extraTransaction(): ExtraTransaction {
        return mDelegate.extraTransaction()
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return mDelegate.onCreateFragmentAnimator()
    }

    override fun getSupportDelegate(): SupportActivityDelegate {
        return mDelegate;
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     */
    override fun post(runnable: Runnable?) {
        mDelegate.post(runnable)
    }

    /**
     * 获取栈内的fragment对象
     */
    fun <T : ISupportFragment> findFragment(fragmentClass: Class<T>): T {
        return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
    }

}