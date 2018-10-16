package com.kermitye.bookmaster.model

import com.huiming.bestnamerobot.rx.RetrofitUtil
import com.huiming.bestnamerobot.rx.Rx
import com.kermitye.bookmaster.api.ZhuiShuApi
import com.kermitye.bookmaster.contract.RankingContract
import com.kermitye.bookmaster.model.bean.RankingBean
import io.reactivex.Observable

/**
 * Created by kermitye on 2018/10/10 16:15
 */
class RankingModel : RankingContract.IRankingModel {
    companion object {
        fun newInstance(): RankingModel = RankingModel()
    }

    override fun getRanking(): Observable<RankingBean> {
        return Rx.get(RetrofitUtil.createApi(ZhuiShuApi::class.java, ZhuiShuApi.BASE_URL).getRanking())
    }
}