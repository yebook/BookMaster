package com.kermitye.bookmaster.model

import com.kermitye.bookmaster.contract.ReadContract

/**
 * Created by kermitye on 2018/10/24 14:49
 */
class ReadModel : ReadContract.IReadModel {
    companion object {
        fun newInstance(): ReadModel = ReadModel()
    }
}