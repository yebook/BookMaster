package com.kermitye.baselib.dialog

import android.widget.TextView
import android.util.SparseArray
import android.view.View


/**
 * Created by kermitye on 2018/10/18 19:14
 */
class DialogViewHolder(view: View) {
    private var convertView: View

    init {
        convertView = view
    }

    companion object {
        fun create(view: View): DialogViewHolder {
            return DialogViewHolder(view)
        }
    }
    fun getConvertView(): View {
        return convertView
    }
}