package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView

/**
 * Created by kermitye on 2018/10/8 17:37
 */
interface BookDetailContract {
    abstract class BookDetailPresenter: BasePresenter<IBookDetailModel, IBookDetailView>()

    interface IBookDetailModel: IBaseModel

    interface IBookDetailView : IBaseView
}