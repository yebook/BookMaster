package com.kermitye.bookmaster.ui.widget

import android.support.v4.app.FragmentManager
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.kermitye.baselib.dialog.BaseMsgDialog
import com.kermitye.baselib.dialog.DialogViewHolder
import com.kermitye.baselib.dialog.ThemDialog
import com.kermitye.baselib.dialog.ViewConvertListener
import com.kermitye.baselib.ext.setVisible
import com.kermitye.bookmaster.R
import com.kermitye.bookmaster.R.id.dimensions
import com.kermitye.bookmaster.R.id.mRvCatalog
import com.kermitye.bookmaster.adapter.ListDialogAdapter
import kotlinx.android.synthetic.main.layout_list_dialog.view.*

/**
 * Created by kermitye on 2018/10/22 15:51
 */
class ListDialog private constructor(val manager: FragmentManager) {
    private var mDialog: ThemDialog

    private var mData: ArrayList<String> = arrayListOf()
    private val mAdapter by lazy { ListDialogAdapter(mData) }
    private var mItemClickListener: ((Int, String) -> Unit)? = null
    private var mTitle: String = "请选择"

    companion object {
        fun newInstance(manager: FragmentManager): ListDialog = ListDialog(manager)
    }

    init {
        mDialog = ThemDialog.init().setLayoutId(R.layout.layout_list_dialog)
    }

    fun setList(data: List<String>) = apply {
        mData.clear()
        mData.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Int, String) -> Unit) = apply { mItemClickListener = listener }

    fun setTitle(title: String) = apply { mTitle = title }

    fun show(title: String = "") {
        if (title.isNotEmpty())
            mTitle = title
        dismiss()
        mDialog.setConvertListener(object : ViewConvertListener() {
            override fun convertView(holder: DialogViewHolder, dialog: BaseMsgDialog) {
                holder.getConvertView().let { v ->
                    v.mTvCancel.setOnClickListener { dismiss() }
                    v.mTvTitle.text = mTitle
                    v.mRvData.layoutManager = LinearLayoutManager(mDialog.context)
                    v.mRvData.addItemDecoration(DividerItemDecoration(mDialog.context, DividerItemDecoration.VERTICAL))
                    v.mRvData.adapter = mAdapter
                    mAdapter.setOnItemClickListener { adapter, view, position ->
                        mItemClickListener?.let { it(position, mData.get(position)) }
                        dismiss()
                    }
                }
            }
        }).setShowBottom(true).show(manager)
    }

    fun dismiss() {
        if (isShowing())
            mDialog.dismiss()
    }

    fun isShowing(): Boolean = mDialog.isShowing()
}