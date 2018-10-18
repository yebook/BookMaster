package com.kermitye.baselib.dialog

import android.widget.TextView
import android.util.SparseArray
import android.view.View


/**
 * Created by kermitye on 2018/10/18 19:14
 */
class ViewHolder(view: View) {
    private var views: SparseArray<View>
    private var convertView: View

    init {
        convertView = view
        views = SparseArray()
    }

    fun create(view: View): ViewHolder {
        return ViewHolder(view)
    }

    fun <T : View> getView(viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    fun getConvertView(): View {
        return convertView
    }

    fun setText(viewId: Int, text: String) {
        val textView = getView<TextView>(viewId)
        textView.setText(text)
    }

    fun setText(viewId: Int, textId: Int) {
        val textView = getView<TextView>(viewId)
        textView.setText(textId)
    }

    fun setTextColor(viewId: Int, colorId: Int) {
        val textView = getView<TextView>(viewId)
        textView.setTextColor(colorId)
    }

    fun setOnClickListener(viewId: Int, clickListener: View.OnClickListener) {
        val view = getView<View>(viewId)
        view.setOnClickListener(clickListener)
    }

    fun setBackgroundResource(viewId: Int, resId: Int) {
        val view = getView<View>(viewId)
        view.setBackgroundResource(resId)
    }

    fun setBackgroundColor(viewId: Int, colorId: Int) {
        val view = getView<View>(viewId)
        view.setBackgroundColor(colorId)
    }
}