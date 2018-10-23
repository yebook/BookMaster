package com.kermitye.bookmaster.ui.widget

import android.support.v4.app.FragmentManager
import android.widget.TextView
import com.kermitye.baselib.dialog.BaseMsgDialog
import com.kermitye.baselib.dialog.DialogViewHolder
import com.kermitye.baselib.dialog.ThemDialog
import com.kermitye.baselib.dialog.ViewConvertListener
import com.kermitye.baselib.ext.setVisible
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.R.id.mTvTitle
import kotlinx.android.synthetic.main.layout_msg_dialog.view.*

/**
 * Created by kermitye on 2018/10/19 14:39
 */
class MsgDialog private constructor(val manager: FragmentManager) {
    private var mDialog: ThemDialog
    private var mConfimListener: ((dialog: MsgDialog) -> Unit)? = null
    private var mCancelListener: ((dialog: MsgDialog) -> Unit)? = null
    private lateinit var mTitle: String
    private lateinit var mMsg: String

    companion object {
        fun newInstance(manager: FragmentManager): MsgDialog = MsgDialog(manager)
    }

    init {
        mDialog = ThemDialog.init()
                .setLayoutId(R.layout.layout_msg_dialog)
    }

    fun setConfimListener(listener: (dialog: MsgDialog) -> Unit) = apply { mConfimListener = listener }
    fun setCancelListener(listener: (dialog: MsgDialog) -> Unit) = apply { mCancelListener = listener }

    fun show(title: String, msg: String = "") {
        dismiss()
        mMsg = msg
        mTitle = title
        mDialog.setConvertListener(object : ViewConvertListener() {
            override fun convertView(holder: DialogViewHolder, dialog: BaseMsgDialog) {
                holder.getConvertView().let { v ->
                    v.mTvMsgTitle.text = mTitle
                    v.mTvMsgContent.text = mMsg
                    v.mTvMsgContent.setVisible(!mMsg.isEmpty())
                    v.mTvCancel.setOnClickListener {
                        mCancelListener?.let { it(this@MsgDialog) }
                        dismiss()
                    }
                    v.mTvConfim.setOnClickListener { mConfimListener?.let { it(this@MsgDialog) } }
                }
            }
        }).setMargin(30).show(manager)
    }

    fun dismiss() {
        if (isShowing())
            mDialog.dismiss()
    }

    fun isShowing(): Boolean = mDialog.isShowing()
}