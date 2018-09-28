package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView

/**
 * Created by kermitye on 2018/9/28 16:12
 */
interface SearchContract {
    abstract class SearchPresenter : BasePresenter<ISearchModel, ISearchView>()

    interface ISearchModel : IBaseModel
    interface ISearchView: IBaseView

}