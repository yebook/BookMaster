package com.kermitye.baselib.dialog

import android.os.Bundle
import android.support.annotation.LayoutRes

/**
 * Created by kermitye on 2018/10/19 11:44
 */
class ThemDialog : BaseMsgDialog() {
    private var convertListener: ViewConvertListener? = null

    companion object {
        fun init(): ThemDialog = ThemDialog()
    }

    override fun intLayoutId(): Int = layoutId

    override fun convertView(holderDialog: DialogViewHolder, dialog: BaseMsgDialog) {
        convertListener?.convertView(holderDialog, dialog)
    }

    fun setLayoutId(@LayoutRes layoutId: Int) = apply { this.layoutId = layoutId }

    fun setConvertListener(listener: ViewConvertListener) = apply { this.convertListener = listener }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null)
            convertListener = savedInstanceState.getParcelable("listener")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("listener", convertListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        convertListener = null
    }

    fun isShowing(): Boolean = dialog != null && dialog.isShowing
}