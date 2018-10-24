package com.kermitye.bookmaster.presenter

import com.kermitye.bookmaster.contract.ReadContract
import com.kermitye.bookmaster.model.ReadModel

/**
 * Created by kermitye on 2018/10/24 15:41
 */
class ReadPresenterImpl : ReadContract.ReadPresenter() {

    companion object {
        fun newInstance(): ReadPresenterImpl = ReadPresenterImpl()
    }

    override fun getModel(): ReadModel = ReadModel.newInstance()

    override fun onStart() {
    }

}