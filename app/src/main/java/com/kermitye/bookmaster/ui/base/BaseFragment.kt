package com.kermitye.bookmaster.ui.base

import me.yokeyword.fragmentation.ISupportFragment
import com.kermitye.baselib.ui.BaseFragment as LibBaseFragment
import android.os.Bundle
import android.content.Intent
import android.app.Activity
import android.support.annotation.Nullable
import me.yokeyword.fragmentation.anim.FragmentAnimator
import android.view.animation.Animation
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.SupportFragmentDelegate
import android.support.v4.app.FragmentActivity
import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.MvpActivity
import com.kermitye.baselib.mvp.MvpFragment
import com.kermitye.bookmaster.ui.widget.MsgDialog


/**
 * Created by kermitye
 * Date: 2018/9/21 16:24
 * Desc:
 */
abstract class BaseFragment<P: BasePresenter<*, *>> : MvpFragment<P>() {
    val mMsgDialog by lazy { MsgDialog.newInstance(fragmentManager!!) }

}