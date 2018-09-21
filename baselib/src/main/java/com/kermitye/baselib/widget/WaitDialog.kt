package com.kermitye.baselib.widget

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.TextView
import com.kermitye.baselib.R

/**
 * Created by kermitye
 * Date: 2018/8/13 14:35
 * Desc:
 */
class WaitDialog(context: Context) {
    var waitDialog: Dialog? = null
    private var mTv: TextView

    init {
        waitDialog = Dialog(context, R.style.MyDialog)
        val recdialog = View.inflate(context, R.layout.dialog_wait, null)
        mTv = recdialog.findViewById(R.id.tv_dialog_wait) as TextView
        waitDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        waitDialog?.setContentView(recdialog)
        waitDialog?.setCanceledOnTouchOutside(false)
        waitDialog?.setCancelable(true)
    }

    fun show(text_dialog: String = "") {
        dismiss()
        mTv.text = text_dialog
        waitDialog?.show()
    }

    fun isShowing() = waitDialog?.isShowing ?: false

    fun dismiss() {
        if (waitDialog != null && waitDialog?.isShowing ?: false) {
            waitDialog?.dismiss()
        }
    }
}