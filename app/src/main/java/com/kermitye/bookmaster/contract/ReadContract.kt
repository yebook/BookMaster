package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView
import com.kermitye.bookmaster.model.ReadModel

/**
 * Created by kermitye on 2018/10/24 14:47
 */
interface ReadContract {

    abstract class ReadPresenter : BasePresenter<ReadModel, IReadView>()

    interface IReadView : IBaseView {}

    interface IReadModel : IBaseModel {}
}