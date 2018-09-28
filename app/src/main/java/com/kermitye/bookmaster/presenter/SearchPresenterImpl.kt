package com.kermitye.bookmaster.presenter

import com.kermitye.bookmaster.contract.SearchContract
import com.kermitye.bookmaster.model.SearchModel

/**
 * Created by kermitye on 2018/9/28 16:14
 */
class SearchPresenterImpl : SearchContract.SearchPresenter() {

    companion object {
        fun newInstance(): SearchPresenterImpl = SearchPresenterImpl()
    }

    override fun getModel(): SearchContract.ISearchModel = SearchModel.newInstance()

    override fun onStart() {
    }
}