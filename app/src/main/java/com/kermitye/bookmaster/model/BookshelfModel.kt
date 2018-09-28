package com.kermitye.bookmaster.model

import com.kermitye.bookmaster.contract.BookshelfContract

/**
 * Created by kermitye on 2018/9/28 11:09
 */
class BookshelfModel : BookshelfContract.IBookshelfModel {

    companion object {
        fun newInstance(): BookshelfModel = BookshelfModel()
    }

}