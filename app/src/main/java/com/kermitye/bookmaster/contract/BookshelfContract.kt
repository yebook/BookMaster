package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView
import com.kermitye.baselib.mvp.MvpFragment

/**
 * Created by kermitye on 2018/9/28 10:59
 */
interface BookshelfContract {

    abstract class BookshelfPresenter : BasePresenter<IBookshelfModel, IBookshelfView>()

    interface IBookshelfModel : IBaseModel

    interface IBookshelfView: IBaseView
}