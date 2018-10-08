package com.kermitye.bookmaster.model

import com.kermitye.bookmaster.contract.BookDetailContract

/**
 * Created by kermitye on 2018/10/8 17:56
 */
class BookDetailModel: BookDetailContract.IBookDetailModel {
    companion object {
        fun newInstance(): BookDetailModel = BookDetailModel()
    }


}
