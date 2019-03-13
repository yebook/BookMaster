package com.kermitye.bookmaster.presenter

import android.annotation.SuppressLint
import com.kermitye.baselib.ext.excute
import com.kermitye.baselib.net.HttpObserver
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.contract.CatalogContract
import com.kermitye.bookmaster.model.CatalogModel
import com.kermitye.bookmaster.model.bean.AtocBean
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/10/17 15:54
 */
class CatalogPresenterImpl : CatalogContract.CatalogPresenter() {

    companion object {
        fun newInstance(): CatalogPresenterImpl = CatalogPresenterImpl()
    }

    override fun onStart() {
    }

    override fun getModel(): CatalogModel = CatalogModel.newInstance()

    fun getData(bookId: String) {
        mModel?.getData(bookId)?.excute(object : HttpObserver<AtocBean>() {
            override fun onSuccess(t: AtocBean?) {
                mView?.updateBookSource(mModel?.mSource ?: arrayListOf())
                t?.let { mView?.updateData(t.chapters ?: arrayListOf()) }
            }

            override fun onError(e: Throwable) {
                LogUtil.e("getData error: ${e.message}")
            }
        })
    }

    fun getDataBySourceId(position: Int) {
        mModel?.let {
            if (it.mSource.isEmpty()) {
                mView?.updateData(arrayListOf())
                return
            }
            var sourceId = it.mSource.get(position)._id
            it.getCatalog(sourceId).excute(object : HttpObserver<AtocBean>() {
                override fun onSuccess(t: AtocBean?) {
                    t?.let { mView?.updateData(t.chapters ?: arrayListOf()) }
                }
                override fun onError(code: Int, msg: String?) {
                    mView?.updateData(arrayListOf())
                }
            })
        }
    }


}