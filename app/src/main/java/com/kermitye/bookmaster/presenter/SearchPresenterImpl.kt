package com.kermitye.bookmaster.presenter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import com.kermitye.baselib.ext.bindLife
import com.kermitye.baselib.ext.excute
import com.kermitye.baselib.net.HttpObserver
import com.kermitye.baselib.util.LogUtil
import com.kermitye.bookmaster.contract.SearchContract
import com.kermitye.bookmaster.model.SearchModel
import com.kermitye.bookmaster.model.bean.HotWordBean
import com.kermitye.bookmaster.model.bean.KeyWordsBean
import com.kermitye.bookmaster.model.bean.SearchBooksBean
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * Created by kermitye on 2018/9/28 16:14
 */
class SearchPresenterImpl : SearchContract.SearchPresenter() {

    companion object {
        fun newInstance(): SearchPresenterImpl = SearchPresenterImpl()
    }

    var mSearchDisposable: Disposable? = null

    override fun getModel(): SearchContract.ISearchModel = SearchModel.newInstance()

    override fun onStart() {
        getHotWord()
    }

    fun getHotWord() {
        mModel?.getHotWord()?.bindLife(mOwner, Lifecycle.Event.ON_STOP)?.excute(object : HttpObserver<HotWordBean>() {
            override fun onSuccess(t: HotWordBean?) {
                t?.let {
                    mView?.updateHotWord(it.hotWords)
                }
            }

            override fun onError(code: Int, msg: String?) {
            }
        })
    }

    fun getKeyWords(query: String) {
       mModel?.getKeyWrods(query)?.excute(object : HttpObserver<KeyWordsBean>() {

            override fun onSubscribe(d: Disposable) {
                mSearchDisposable = d
            }

            override fun onSuccess(t: KeyWordsBean?) {
                t?.let {
                    mView?.updateKeyWords(it.keywords)
                }
            }

            override fun onError(code: Int, msg: String?) {
            }
        })
    }

    fun getSearchBooks(query: String) {
        if (mSearchDisposable != null && !(mSearchDisposable?.isDisposed ?: true)) {
            mSearchDisposable?.dispose()
        }

        mModel?.getSearchBooks(query)?.excute(object : HttpObserver<SearchBooksBean>() {
            override fun onSuccess(t: SearchBooksBean?) {
                t?.let { mView?.updateSearchBooks(it.books ?: arrayListOf()) }
            }
            override fun onError(code: Int, msg: String?) {
            }
        })
    }

}