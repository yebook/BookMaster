package com.kermitye.bookmaster.contract

import com.kermitye.baselib.mvp.BasePresenter
import com.kermitye.baselib.mvp.IBaseModel
import com.kermitye.baselib.mvp.IBaseView
import com.kermitye.bookmaster.model.bean.Male
import com.kermitye.bookmaster.model.bean.RankingBean
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/10/10 16:12
 */
interface RankingContract {
    abstract class RankingPresenter : BasePresenter<IRankingModel, IRankingView>()

    interface IRankingModel : IBaseModel {
        fun getRanking() : Observable<RankingBean>
    }
    interface IRankingView : IBaseView {
        fun updateData(data: List<Male>)
    }
}