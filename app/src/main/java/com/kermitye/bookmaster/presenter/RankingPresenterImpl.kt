package com.kermitye.bookmaster.presenter

import com.kermitye.baselib.ext.excute
import com.kermitye.baselib.net.HttpObserver
import com.kermitye.bookmaster.contract.RankingContract
import com.kermitye.bookmaster.model.RankingModel
import com.kermitye.bookmaster.model.bean.RankingBean

/**
 * Created by kermitye on 2018/10/10 16:13
 */
class RankingPresenterImpl : RankingContract.RankingPresenter() {
    companion object {
        fun newInstance(): RankingPresenterImpl = RankingPresenterImpl()
    }

    override fun getModel(): RankingContract.IRankingModel = RankingModel.newInstance()

    override fun onStart() {
//        getRanking()
    }

    fun getRanking() {
        mModel?.getRanking()?.excute(object : HttpObserver<RankingBean>() {
            override fun onSuccess(t: RankingBean?) {
                t?.let { mView?.updateData(t.male) }
            }
        })
    }
}